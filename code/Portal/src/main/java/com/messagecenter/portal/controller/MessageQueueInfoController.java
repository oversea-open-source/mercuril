package com.messagecenter.portal.controller;

import com.messagecenter.portal.entity.MessageQueueInfo;
import com.messagecenter.portal.entity.base.BaseResponse;
import com.messagecenter.portal.entity.base.PageInfoResult;
import com.messagecenter.portal.entity.base.StatusCode;
import com.messagecenter.portal.exception.BusinessException;
import com.messagecenter.portal.service.MessageQueueInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jared on 16/12/6.
 */
@RestController
public class MessageQueueInfoController {
    private static Logger logger = LoggerFactory.getLogger(MessageQueueInfoController.class);

    @Autowired
    MessageQueueInfoService messageQueueInfoService;


    @RequestMapping(value = "/api/SaveMessageQueueInfo", method = RequestMethod.POST)
    public BaseResponse saveMessageQueueInfo(@Validated @RequestBody MessageQueueInfo messageQueueInfo) throws BusinessException {
        messageQueueInfoService.saveMessageQueueInfo(messageQueueInfo);
        BaseResponse response = new BaseResponse();
        response.setMessage("Message has been saved successfully");
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/api/MessageList", method = RequestMethod.GET)
    public BaseResponse<PageInfoResult<MessageQueueInfo>> getMessageList(@RequestParam int pageNum, @RequestParam int pageSize) {
        PageInfoResult<MessageQueueInfo> result = messageQueueInfoService.getMessageQueueInfoList(pageNum, pageSize);
        BaseResponse<PageInfoResult<MessageQueueInfo>> response = new BaseResponse<>();
        response.setData(result);
        response.setCode(StatusCode.SUCCESS);
        return response;
    }

}
