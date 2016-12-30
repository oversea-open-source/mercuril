package com.messagecenter.portal.controller;

import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.common.entity.base.StatusCode;
import com.messagecenter.portal.service.MessageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jared on 16/12/13.
 */
@RestController
public class MessageLogController {

    @Autowired
    MessageLogService service;

    @RequestMapping(value = "/api/MessageLog", method = RequestMethod.GET)
    public BaseResponse<PageInfoResult<MessageLog>> getLogList(@RequestParam(required = false) Integer pageNum,
                                                               @RequestParam(required = false) Integer pageSize,
                                                               @RequestParam(required = false) String messageQueueName) {
        PageInfoResult<MessageLog> pageInfoResult = service.getLogList(pageNum, pageSize, messageQueueName);
        return new BaseResponse<>(pageInfoResult);
    }
}
