package com.messagecenter.client.utils;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * Created by Jared on 16/12/19.
 */
public class QueueUtils {
    public static boolean isQueueExist(RabbitTemplate rabbitTemplate, String queueName) {
        AMQP.Queue.DeclareOk ok = rabbitTemplate.execute(channel -> {
            try {
                return channel.queueDeclarePassive(queueName);
            } catch (Exception e) {
                return null;
            }
        });

        return ok != null;
    }
}
