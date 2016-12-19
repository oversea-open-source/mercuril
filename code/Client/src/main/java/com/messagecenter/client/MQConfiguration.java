package com.messagecenter.client;

import com.messagecenter.client.service.MessageQueueInfoService;
import com.messagecenter.client.service.ReceiveService;
import com.messagecenter.client.utils.QueueUtils;
import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageQueueInfo;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
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
    MessageListenerAdapter messageListenerAdapter(ReceiveService receiver) {
        return new MessageListenerAdapter(receiver, "received");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        List<String> messageQueueNames = messageQueueInfoService.getMessageQueueNameList();
        List<String> existMessageQueue = messageQueueNames.stream().filter(messageQueueName -> QueueUtils.isQueueExist(rabbitTemplate, messageQueueName)).collect(Collectors.toList());

        container.setQueueNames(existMessageQueue.toArray(new String[existMessageQueue.size()]));
        container.setMessageListener(messageListenerAdapter);
        return container;
    }


    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(Const.EXCHANGE_NAME);
    }
}
