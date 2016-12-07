package com.messagecenter.portal.service;

import com.messagecenter.portal.controller.Const;
import com.messagecenter.portal.entity.MessageQueueInfo;
import com.messagecenter.portal.exception.BusinessException;
import com.messagecenter.portal.mapper.MessageQueueInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/7.
 */
@Service
public class MessageQueueInfoService {

    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;

    public void saveMessageQueueInfo(MessageQueueInfo messageQueueInfo) throws BusinessException {

        MessageQueueInfo query = new MessageQueueInfo();
        query.setMessageQueueName(messageQueueInfo.getMessageQueueName());
        List<MessageQueueInfo> existInfos = messageQueueInfoMapper.getMessageQueueInfoList(query);

        if (existInfos == null || existInfos.size() == 0) {
            messageQueueInfo.setInUser(Const.IN_USER_NAME);
            messageQueueInfo.setInDate(new Date());
            messageQueueInfo.setLastEditDate(new Date());
            messageQueueInfo.setLastEditUser(Const.IN_USER_NAME);
            messageQueueInfoMapper.saveMessageQueueInfoList(messageQueueInfo);
        } else {
            throw new BusinessException("Message queue with same name already exists");
        }
    }
}
