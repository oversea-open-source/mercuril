package com.messagecenter.client.task;

import com.messagecenter.client.service.MessageQueueInfoService;
import com.messagecenter.client.utils.QueueUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jared on 16/12/19.
 */
@Component
public class QueueTask {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    SimpleMessageListenerContainer simpleMessageListenerContainer;
    @Autowired
    MessageQueueInfoService messageQueueInfoService;

    @Scheduled(fixedRate = 5000)
    public void refreshListenerContainer() {
        List<String> messageQueueNames = messageQueueInfoService.getMessageQueueNameList();
        List<String> existMessageQueueNames = messageQueueNames.stream().filter(messageQueueName -> QueueUtils.isQueueExist(rabbitTemplate, messageQueueName)).collect(Collectors.toList());
        simpleMessageListenerContainer.addQueueNames(existMessageQueueNames.toArray(new String[existMessageQueueNames.size()]));
    }
}
