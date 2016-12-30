package com.messagecenter.portal.controller;

import com.messagecenter.common.entity.MessageLogDetail;
import com.messagecenter.common.entity.base.BaseResponse;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.portal.service.MessageLogDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jared on 16/12/30.
 */
@RestController
public class MessageLogDetailController {
    @Autowired
    MessageLogDetailService messageLogDetailService;

    @RequestMapping(value = "/api/MessageLogDetail", method = RequestMethod.GET)
    public BaseResponse<PageInfoResult<MessageLogDetail>> getLogDetailList(@RequestParam(required = false) Integer pageNum,
                                                                           @RequestParam(required = false) Integer pageSize,
                                                                           @RequestParam(required = false) Integer messageLogId) {
        PageInfoResult<MessageLogDetail> list = messageLogDetailService.getMessageLogDetailList(pageNum, pageSize, messageLogId);
        return new BaseResponse<>(list);
    }
}
