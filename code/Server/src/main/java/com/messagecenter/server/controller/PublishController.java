package com.messagecenter.server.controller;

import com.messagecenter.common.entity.PublishMessageInfo;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.StatusCode;
import com.messagecenter.common.exception.BusinessException;
import com.messagecenter.server.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jared on 16/12/13.
 */
@RestController
public class PublishController {
    @Autowired
    PublishService publishService;

    @RequestMapping(value = "/api/Publish", method = RequestMethod.POST)
    public BaseResponse publishMessage(@RequestBody PublishMessageInfo publishMessageInfo) throws BusinessException {
        publishService.publishMessage(publishMessageInfo);
        BaseResponse response = new BaseResponse();
        response.setMessage("Message has been published");
        return response;
    }
}
