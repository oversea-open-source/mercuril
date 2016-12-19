package com.messagecenter.client;

import com.messagecenter.client.service.MessageQueueInfoService;
import com.messagecenter.client.service.ReceiveService;
import com.messagecenter.common.entity.MessageQueueInfo;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jared on 16/12/16.
 */
@Configuration
public class MQConfiguration {
    public static final String exchangeName = "Mercuril-Exchange";

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
        List<String> messageQueueInfos = messageQueueInfoService.getMessageQueueNameList();
        List<String> existMessageQueue = new ArrayList<>();
        for (String messageQueueName : messageQueueInfos) {
            AMQP.Queue.DeclareOk ok = rabbitTemplate.execute(channel -> {
                try {
                    return channel.queueDeclarePassive(messageQueueName);
                } catch (Exception e) {
                    return null;
                }
            });
            if (ok != null) {
                existMessageQueue.add(messageQueueName);
            }
        }

        container.setQueueNames(existMessageQueue.toArray(new String[existMessageQueue.size()]));
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

}
