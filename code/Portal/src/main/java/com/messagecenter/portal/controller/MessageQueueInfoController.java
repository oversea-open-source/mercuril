package com.messagecenter.portal.controller;

import com.messagecenter.portal.entity.MessageQueueInfo;
import com.messagecenter.portal.mapper.MessageQueueInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jared on 16/12/6.
 */
@RestController
public class MessageQueueInfoController {

    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;

    @RequestMapping(value = "/api/SaveMessageQueueInfo", method = RequestMethod.POST)
    public void saveMessageQueueInfo(MessageQueueInfo messageQueueInfo) {
        messageQueueInfoMapper.saveMessageQueueInfoList(messageQueueInfo);
    }
}
