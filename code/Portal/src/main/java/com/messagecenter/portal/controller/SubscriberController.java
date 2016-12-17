package com.messagecenter.portal.controller;

import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.common.entity.base.StatusCode;
import com.messagecenter.common.exception.BusinessException;
import com.messagecenter.portal.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jared on 16/12/12.
 */
@RestController
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;

    @RequestMapping(value = "/api/Subscriber", method = RequestMethod.GET)
    public BaseResponse<PageInfoResult<MessageQueueSubscriber>> getSubscriberListByMessageQueueId(@RequestParam(required = false) Integer pageNum, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) Integer messageQueueId, @RequestParam(required = false) Integer id) {
        PageInfoResult<MessageQueueSubscriber> result = subscriberService.getSubscriberListByMessageQueueId(pageNum, pageSize, messageQueueId, id);
        return new BaseResponse(result);
    }

    @RequestMapping(value = "/api/Subscriber", method = RequestMethod.POST)
    public BaseResponse saveSubscriber(@Validated @RequestBody MessageQueueSubscriber subscriber, @RequestParam(required = false) boolean isEdit) throws BusinessException {
        if (isEdit) {
            subscriberService.updateSubscriber(subscriber);
        } else {
            subscriberService.saveSubscriber(subscriber);
        }
        BaseResponse response = new BaseResponse();
        response.setMessage("Subscriber has been saved successfully");
        return response;
    }
}
