package com.messagecenter.client.service;

import com.messagecenter.client.mapper.MessageLogDetailMapper;
import com.messagecenter.client.mapper.SubscriberMapper;
import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLogDetail;
import com.messagecenter.common.entity.MessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/22.
 */
@Service
public class MessageLogDetailService {

    @Autowired
    MessageLogDetailMapper messageLogDetailMapper;
    @Autowired
    SubscriberMapper subscriberMapper;

    Logger logger = LoggerFactory.getLogger(MessageLogDetailService.class);

    /**
     * Retry
     */
    public void retryLogDetail() {
        List<MessageLogDetail> needRetryList = messageLogDetailMapper.getMessageLogDetail(MessageStatus.SENT_TO_SUB_FAILED, true, false, new Date());
        for (MessageLogDetail detail : needRetryList) {
            this.reCallSubscriber(detail, false);
        }
    }


    /**
     * Replay
     */
    public void autoReplayLogDetail() {
        List<MessageLogDetail> needAutoReplay = messageLogDetailMapper.getMessageLogDetail(MessageStatus.SENT_TO_SUB_FAILED, false, true, new Date());
        for (MessageLogDetail detail : needAutoReplay) {
            this.reCallSubscriber(detail, true);
        }
    }


    private void reCallSubscriber(MessageLogDetail messageLogDetail, boolean autoReplay) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> entity = new HttpEntity<>(messageLogDetail.getMessageLog().getMessageRaw(), headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(messageLogDetail.getMessageQueueSubscriber().getSubscriberApiUrl(), HttpMethod.POST, entity, String.class);
            HttpStatus status = responseEntity.getStatusCode();

            updateLogDetailAfterRetry(messageLogDetail, status.is2xxSuccessful(), null, autoReplay);
        } catch (Exception e) {
            logger.error(e.getMessage());
            updateLogDetailAfterRetry(messageLogDetail, false, e.getMessage(), autoReplay);
        }
    }

    private void updateLogDetailAfterRetry(MessageLogDetail messageLogDetail, boolean isSuccess, String failedReason, boolean autoReplay) {

        messageLogDetail.setMessageStatus(isSuccess ? MessageStatus.SENT_TO_SUB_SUCCESS : MessageStatus.SENT_TO_SUB_FAILED);
        if (!isSuccess) {
            if (autoReplay) {
                messageLogDetail.setFailedRetryCount(1);
            } else {
                messageLogDetail.setFailedRetryCount(messageLogDetail.getFailedRetryCount() + 1);
            }
        }

        if (autoReplay) {
            messageLogDetail.setLastReplayFinishedDate(new Date());
        }

        messageLogDetail.setFailedReason(failedReason);
        messageLogDetail.setLastEditDate(new Date());
        messageLogDetail.setLastEditUser(Const.IN_USER_NAME);

        messageLogDetailMapper.updateMessageLogDetail(messageLogDetail);
    }
}
