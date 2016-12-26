package com.messagecenter.client.controller;

import com.messagecenter.client.service.MessageLogDetailService;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jared on 16/12/19.
 */
@RestController
public class IndexController {
    @Autowired
    MessageLogDetailService messageLogDetailService;


    @RequestMapping(value = "/api/foo", method = RequestMethod.POST)
    public BaseResponse foo() {
        BaseResponse response = new BaseResponse();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/api/retry", method = RequestMethod.GET)
    public BaseResponse retry() {
        messageLogDetailService.retryLogDetail();
        BaseResponse response = new BaseResponse();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/api/autoReplay", method = RequestMethod.GET)
    public BaseResponse autoReplay() {
        messageLogDetailService.autoReplayLogDetail();
        BaseResponse response = new BaseResponse();
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

}
