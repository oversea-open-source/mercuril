package com.messagecenter.server.controller;

import com.messagecenter.common.entity.PublishMessageInfo;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.StatusCode;
import com.messagecenter.common.exception.BusinessException;
import com.messagecenter.server.Application;
import com.messagecenter.server.MQConfiguration;
import com.messagecenter.server.service.PublishService;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jared on 16/12/13.
 */
@RestController
public class PublishController {
    @Autowired
    PublishService publishService;
    @Autowired
    ConfigurableApplicationContext applicationContext;


    @RequestMapping(value = "/api/Publish", method = RequestMethod.POST)
    public BaseResponse publishMessage(@RequestBody PublishMessageInfo publishMessageInfo) throws BusinessException {
        publishService.publishMessage(publishMessageInfo);
        BaseResponse response = new BaseResponse();
        response.setMessage("Message has been published");

        return response;
    }

    @RequestMapping(value = "/api/retrySendMQ", method = RequestMethod.GET)
    public BaseResponse retrySendMQ() throws BusinessException {
        publishService.retrySendMQ();
        BaseResponse response = new BaseResponse();
        response.setMessage("Message has been resent");

        return response;
    }
}
