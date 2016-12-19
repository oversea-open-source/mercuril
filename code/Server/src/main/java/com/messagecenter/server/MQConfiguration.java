package com.messagecenter.server;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Map;

/**
 * Created by Jared on 16/12/16.
 */
@Configuration
public class MQConfiguration {
    public static final String exchangeName = "Mercuril-Exchange";
//    public static final String queueName = "hello";


    @Autowired
    ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

//
//    @Bean
//    public Queue queue() {
//        return new Queue(queueName, true);
//    }
//
//    @Bean
//    MessageListenerAdapter messageListenerAdapter(ReceiverService receiver) {
//        return new MessageListenerAdapter(receiver, "receiveFooQueue");
//    }
//
//    @Bean
//    @Scope("request")
//    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(queue().getName());
//        container.setMessageListener(messageListenerAdapter);
//        return container;
//    }

//
//    @Bean
//    @Scope("request")
//    public DirectExchange exchange() {
//        return new DirectExchange(exchangeName);
//    }
//
//    @Bean
//    @Scope("request")
//    public Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(queue().getName());
//    }

//    @Bean
//    @Scope("request")
//    public Sender mySender() {
//        return new Sender();
//    }

}
