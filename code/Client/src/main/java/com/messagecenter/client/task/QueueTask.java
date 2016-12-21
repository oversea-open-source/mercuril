package com.messagecenter.client.task;

import com.messagecenter.client.service.MessageQueueInfoService;
import com.messagecenter.client.utils.QueueUtils;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
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
    @Autowired
    ConnectionFactory connectionFactory;
    @Autowired
    MessageListenerAdapter messageListenerAdapter;


    @Scheduled(fixedRate = 5000)
    public void refreshListenerContainer() {
        List<String> messageQueueNames = messageQueueInfoService.getMessageQueueNameList();
        List<String> existMessageQueueNames = messageQueueNames.stream().filter(messageQueueName -> QueueUtils.isQueueExist(rabbitTemplate, messageQueueName)).collect(Collectors.toList());
        if (existMessageQueueNames.size() > 0) {
            if (simpleMessageListenerContainer == null) {
                simpleMessageListenerContainer = new SimpleMessageListenerContainer();
                simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//                simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL);
                simpleMessageListenerContainer.setQueueNames(existMessageQueueNames.toArray(new String[existMessageQueueNames.size()]));
                simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
                simpleMessageListenerContainer.start();
            } else {
                simpleMessageListenerContainer.setQueueNames(existMessageQueueNames.toArray(new String[existMessageQueueNames.size()]));
            }
        }
    }
}
