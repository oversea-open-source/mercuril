package com.messagecenter.portal.job;

import com.messagecenter.common.config.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jared on 16/12/26.
 */
@Component
public class RetryJob {
    Logger logger = LoggerFactory.getLogger(RetryJob.class);

    @Scheduled(fixedRate = 10000)
    public void retryPubMQ() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(Const.SERVER_HOST + "/api/retrySendMQ", HttpMethod.GET, httpEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("Retry pub to MQ successfully");
        } else {
            logger.error("Retry pub to MQ failed:" + responseEntity.getStatusCode().getReasonPhrase());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void retryCallSub() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(Const.CLIENT_HOST + "/api/retry", HttpMethod.GET, httpEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("Retry to call subscriber successfully");
        } else {
            logger.error("Retry to call subscriber failed:" + responseEntity.getStatusCode().getReasonPhrase());
        }
    }

    @Scheduled(fixedRate = 10000)
    public void replayCallSub() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(Const.CLIENT_HOST + "/api/autoReplay", HttpMethod.GET, httpEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            logger.info("Replay calling subscriber successfully");
        } else {
            logger.error("Replay calling subscriber failed:" + responseEntity.getStatusCode().getReasonPhrase());
        }
    }
}
