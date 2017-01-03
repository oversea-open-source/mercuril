package com.messagecenter.server.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageQueueInfo;
import com.messagecenter.common.entity.MessageStatus;
import com.messagecenter.common.entity.PublishMessageInfo;
import com.messagecenter.common.exception.BusinessException;
import com.messagecenter.common.utils.EncryptUtils;
import com.messagecenter.server.MQConfiguration;
import com.messagecenter.server.mapper.MessageLogMapper;
import com.messagecenter.server.mapper.MessageQueueInfoMapper;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/13.
 */
@Service
public class PublishService {

    Logger logger = LoggerFactory.getLogger(PublishService.class);

    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;
    @Autowired
    MessageLogMapper messageLogMapper;
    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;

    /**
     * Publish message to MQ
     *
     * @param publishMessageInfo
     * @throws BusinessException
     */
    public void publishMessage(PublishMessageInfo publishMessageInfo) throws BusinessException {
        MessageQueueInfo messageQueueInfo = messageQueueInfoMapper.getMessageQueueInfoByName(publishMessageInfo.getMessageName());
        if (messageQueueInfo != null) {
            String publishPassword = messageQueueInfo.getPublishPassword();
            if (!TextUtils.isEmpty(publishPassword)) {
                if (TextUtils.isEmpty(publishMessageInfo.getPublishPassword()) || !EncryptUtils.match(publishMessageInfo.getPublishPassword(), publishPassword)) {
                    throw new BusinessException("Publish password is incorrect");
                }
            }
            MessageLog messageLog = new MessageLog();
            messageLog.setMessageQueueName(messageQueueInfo.getMessageQueueName());
            messageLog.setMessageRaw(publishMessageInfo.getMessageBody());
            messageLog.setMessageQueueInfoId(messageQueueInfo.getId());
            messageLog.setMessageStatus(MessageStatus.RECEIVED);
            messageLog.setActive(true);
            messageLog.setInUser(Const.IN_USER_NAME);
            messageLog.setInDate(new Date());
            messageLog.setLastEditUser(Const.IN_USER_NAME);
            messageLog.setLastEditDate(new Date());
            messageLogMapper.saveMessageLog(messageLog);
            try {
                sendToMQ(messageLog);
                updateStatus(messageLog, MessageStatus.SENT_TO_MQ_SUCCESS, null, false);
            } catch (Exception e) {
                logger.error("Send message error: " + e.getMessage());
                updateStatus(messageLog, MessageStatus.SENT_TO_MQ_FAILED, e.getMessage(), false);
            }
        } else {
            throw new BusinessException(String.format("Message queue with name '%1$s' not exists", publishMessageInfo.getMessageName()));
        }
    }

    /**
     * get messages that need to be retried and re-send them to MQ
     *
     * @throws BusinessException
     */
    public void retrySendMQ() throws BusinessException {
        List<MessageLog> messageLogList = messageLogMapper.getMessageLogNeedRetry(MessageStatus.SENT_TO_MQ_FAILED, Const.MAX_RETRY_COUNT);
        for (MessageLog messageLog : messageLogList) {
            reSendMQ(messageLog);
        }
    }

    /**
     * Retry sending message to MQ
     *
     * @throws BusinessException
     */
    public void reSendMQ(MessageLog messageLog) throws BusinessException {

        MessageQueueInfo messageQueueInfo = messageQueueInfoMapper.getMessageQueueInfoById(messageLog.getMessageQueueInfoId());
        messageLog.setMessageQueueName(messageQueueInfo.getMessageQueueName());

        if (messageLog.getMessageStatus() == MessageStatus.SENT_TO_MQ_FAILED) {
            if (messageLog.getFailedRetryCount() < Const.MAX_RETRY_COUNT) {
                try {
                    sendToMQ(messageLog);
                    updateStatus(messageLog, MessageStatus.SENT_TO_MQ_FAILED, null, true);
                } catch (Exception e) {
                    logger.error("Send message error: " + e.getMessage());
                    updateStatus(messageLog, MessageStatus.SENT_TO_MQ_FAILED, e.getMessage(), true);
                }
            } else {
                throw new BusinessException(String.format("Message with id '%1$d' retry count reached '%2$d'", messageLog.getId(), Const.MAX_RETRY_COUNT));
            }
        } else {
            throw new BusinessException(String.format("Message with incorrect status '%1$d' can not be re-send to MQ", messageLog.getMessageStatus()));
        }
    }

    /**
     * Send message to MQ
     *
     * @param messageLog
     */
    private void sendToMQ(MessageLog messageLog) throws Exception {
        Queue queue = new Queue(messageLog.getMessageQueueName());
        DirectExchange exchange = new DirectExchange(Const.EXCHANGE_NAME);

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(queue.getName()));

        rabbitTemplate.convertAndSend(exchange.getName(), queue.getName(), objectMapper.writeValueAsString(messageLog));
    }

    private void updateStatus(MessageLog messageLog, int messageStatus, String failedReason, boolean isRetry) {
        if (isRetry) {
            messageLog.setFailedRetryCount(messageLog.getFailedRetryCount() + 1);
        }
        messageLog.setLastEditUser(Const.IN_USER_NAME);
        messageLog.setLastEditDate(new Date());
        messageLog.setMessageStatus(messageStatus);
        messageLog.setFailedReason(failedReason);
        messageLogMapper.updateMessageLog(messageLog);
    }
}
