package com.messagecenter.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.messagecenter.client.mapper.MessageLogMapper;
import com.messagecenter.client.mapper.SubscriberMapper;
import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.MessageStatus;
import com.messagecenter.common.entity.base.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/19.
 */
@Component
public class ReceiveService {

    Logger logger = LoggerFactory.getLogger(ReceiveService.class);

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessageLogMapper messageLogMapper;
    @Autowired
    SubscriberMapper subscriberMapper;

    public void received(String msg) {
        System.out.print("received: " + msg);
        try {
            MessageLog messageLog = objectMapper.readValue(msg, MessageLog.class);
            if (messageLog != null && messageLog.getId() > 0) {

                updateStatus(messageLog, MessageStatus.SENDER_RECEIVED, null);

                callSubscriber(messageLog);

            } else {
                logger.error("client received null message or message with invalid id from MQ");
            }
        } catch (IOException e) {
            logger.error("client received invalid format message from MQ");
            e.printStackTrace();
        }
    }

    private void callSubscriber(MessageLog messageLog) {
        List<MessageQueueSubscriber> subscribers = subscriberMapper.getSubscriberList(messageLog.getMessageQueueInfoId());
        RestTemplate restTemplate = new RestTemplate();
        for (MessageQueueSubscriber subscriber : subscribers) {
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<>(messageLog.getMessageRaw(), headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(subscriber.getSubscriberApiUrl(), HttpMethod.POST, entity, String.class);
            HttpStatus status = responseEntity.getStatusCode();

            if (status.is2xxSuccessful()) {
                updateStatus(messageLog, MessageStatus.SENT_TO_SUB_SUCCESS, null);
            } else {
                updateStatus(messageLog, MessageStatus.SENT_TO_SUB_FAILED, status.value() + " " + responseEntity.getBody());
            }
        }
    }

    private void updateStatus(MessageLog messageLog, int messageStatus, String failedReason) {
        messageLog.setLastEditUser(Const.IN_USER_NAME);
        messageLog.setLastEditDate(new Date());
        messageLog.setMessageStatus(messageStatus);
        messageLog.setFailedReason(failedReason);
        messageLogMapper.updateMessageLog(messageLog);
    }
}
