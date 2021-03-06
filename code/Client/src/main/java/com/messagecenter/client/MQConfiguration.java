package com.messagecenter.client;

import com.messagecenter.client.service.MessageQueueInfoService;
import com.messagecenter.client.listener.ReceiverMessageListener;
import com.messagecenter.client.utils.QueueUtils;
import com.messagecenter.common.config.Const;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jared on 16/12/16.
 */
@Configuration
public class MQConfiguration {
    @Autowired
    MessageQueueInfoService messageQueueInfoService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Bean
    MessageListenerAdapter messageListenerAdapter(ReceiverMessageListener receiverMessageListener) {
        return new MessageListenerAdapter(receiverMessageListener);
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {

        List<String> messageQueueNames = messageQueueInfoService.getMessageQueueNameList();
        List<String> existMessageQueue = messageQueueNames.stream().filter(messageQueueName -> QueueUtils.isQueueExist(rabbitTemplate, messageQueueName)).collect(Collectors.toList());
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();

        if (existMessageQueue.size() > 0) {
            container.setConnectionFactory(connectionFactory);
//            container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            container.setQueueNames(existMessageQueue.toArray(new String[existMessageQueue.size()]));
            container.setMessageListener(messageListenerAdapter);
            return container;
        }
        return null;
    }


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(Const.EXCHANGE_NAME);
    }
}
