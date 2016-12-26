package com.messagecenter.client.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagecenter.client.mapper.MessageLogDetailMapper;
import com.messagecenter.client.mapper.MessageLogMapper;
import com.messagecenter.client.mapper.SubscriberMapper;
import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageLogDetail;
import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.MessageStatus;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/19.
 */
@Component
public class ReceiverMessageListener implements ChannelAwareMessageListener {

    Logger logger = LoggerFactory.getLogger(ReceiverMessageListener.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessageLogMapper messageLogMapper;
    @Autowired
    SubscriberMapper subscriberMapper;
    @Autowired
    MessageLogDetailMapper logDetailMapper;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String msgBody = new String(message.getBody(), "UTF-8");
        logger.info("received: " + msgBody);

        MessageLog messageLog = objectMapper.readValue(msgBody, MessageLog.class);
        if (messageLog != null && messageLog.getId() > 0) {

            updateStatus(messageLog, MessageStatus.SENDER_RECEIVED, null);

            callSubscriber(messageLog);
        } else {
            logger.error("client received null message or message with invalid id from MQ");
        }

    }

    private void callSubscriber(MessageLog messageLog) {
        List<MessageQueueSubscriber> subscribers = subscriberMapper.getSubscriberList(messageLog.getMessageQueueInfoId());
        RestTemplate restTemplate = new RestTemplate();
        for (MessageQueueSubscriber subscriber : subscribers) {

            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                HttpEntity<String> entity = new HttpEntity<>(messageLog.getMessageRaw(), headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(subscriber.getSubscriberApiUrl(), HttpMethod.POST, entity, String.class);
                HttpStatus status = responseEntity.getStatusCode();

                saveLogDetail(status.is2xxSuccessful(), messageLog, subscriber, null);
            } catch (Exception e) {
                logger.error(e.getMessage());
                saveLogDetail(false, messageLog, subscriber, e.getMessage());
            }
        }
    }

    private void saveLogDetail(boolean isSuccess, MessageLog messageLog, MessageQueueSubscriber subscriber, String failedReason) {
        MessageLogDetail messageLogDetail = new MessageLogDetail();

        messageLogDetail.setMessageStatus(isSuccess ? MessageStatus.SENT_TO_SUB_SUCCESS : MessageStatus.SENT_TO_SUB_FAILED);
        messageLogDetail.setMessageLogId(messageLog.getId());
        messageLogDetail.setMessageQueueSubscriberId(subscriber.getId());
        messageLogDetail.setFailedReason(failedReason);
        messageLogDetail.setLastEditDate(new Date());
        messageLogDetail.setLastReplayFinishedDate(new Date());
        messageLogDetail.setInDate(new Date());
        messageLogDetail.setActive(true);
        messageLogDetail.setInUser(Const.IN_USER_NAME);
        messageLogDetail.setLastEditUser(Const.IN_USER_NAME);

        logDetailMapper.saveMessageLogDetail(messageLogDetail);
    }

    private void updateStatus(MessageLog messageLog, int messageStatus, String failedReason) {
        messageLog.setLastEditUser(Const.IN_USER_NAME);
        messageLog.setLastEditDate(new Date());
        messageLog.setMessageStatus(messageStatus);
        messageLog.setFailedReason(failedReason);
        messageLogMapper.updateMessageLog(messageLog);
    }


}
