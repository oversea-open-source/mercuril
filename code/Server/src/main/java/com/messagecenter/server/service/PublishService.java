package com.messagecenter.server.service;

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
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jared on 16/12/13.
 */
@Service
public class PublishService {
    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;
    @Autowired
    MessageLogMapper messageLogMapper;
    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void publishMessage(PublishMessageInfo publishMessageInfo) throws BusinessException {
        MessageQueueInfo messageQueueInfo = messageQueueInfoMapper.getMessageQueueInfoByName(publishMessageInfo.getMessageName());
        if (messageQueueInfo != null) {
            String publishPassword = publishMessageInfo.getPublishPassword();
            if (!TextUtils.isEmpty(publishPassword)) {
                if (!EncryptUtils.match(publishPassword, messageQueueInfo.getPublishPassword())) {
                    throw new BusinessException("Publish password is incorrect");
                }
            }
            MessageLog messageLog = new MessageLog();
            messageLog.setMessageQueueName(publishMessageInfo.getMessageName());
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
            } catch (Exception e) {
                updateStatus(messageLog, MessageStatus.SENT_TO_MQ_FAILED, e.getCause().toString());
            }
        } else {
            throw new BusinessException(String.format("Message queue with name '%1$s' not exists", publishMessageInfo.getMessageName()));
        }
    }

    /**
     * Send message to MQ
     *
     * @param messageLog
     */
    private void sendToMQ(MessageLog messageLog) throws Exception {
        Queue queue = new Queue(messageLog.getMessageQueueName());
        DirectExchange exchange = new DirectExchange(MQConfiguration.exchangeName);

        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareExchange(exchange);
        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(queue.getName()));
        rabbitTemplate.convertAndSend(exchange.getName(), queue.getName(), messageLog.toString());

        updateStatus(messageLog, MessageStatus.SENT_TO_MQ_SUCCESS, null);
    }

    private void updateStatus(MessageLog messageLog, int messageStatus, String failedReason) {
        messageLog.setLastEditUser(Const.IN_USER_NAME);
        messageLog.setLastEditDate(new Date());
        messageLog.setMessageStatus(messageStatus);
        messageLog.setFailedReason(failedReason);
        messageLogMapper.updateMessageLog(messageLog);
    }
}
