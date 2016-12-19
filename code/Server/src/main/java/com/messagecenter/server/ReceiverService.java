package com.messagecenter.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReceiverService {

//    @RabbitListener(queues = "hello")
    public void receiveFooQueue(String msg) {
        System.out.println("Received " + msg);
    }
}