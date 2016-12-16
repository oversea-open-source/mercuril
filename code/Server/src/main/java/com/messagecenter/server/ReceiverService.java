package com.messagecenter.server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiverService {

//    @RabbitListener(queues = "hello")
    public void receiveFooQueue(String msg) {
        System.out.println("Received " + msg);
    }
}