package com.messagecenter.server.service;

import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageQueueInfo;
import com.messagecenter.common.entity.MessageStatus;
import com.messagecenter.common.entity.PublishMessageInfo;
import com.messagecenter.common.exception.BusinessException;
import com.messagecenter.server.mapper.MessageLogMapper;
import com.messagecenter.server.mapper.MessageQueueInfoMapper;
import com.messagecenter.server.utils.EncryptUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jared on 16/12/13.
 */
@Service
public class PublishService {
    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;
    @Autowired
    MessageLogMapper messageLogMapper;

    public void publishMessage(PublishMessageInfo publishMessageInfo) throws BusinessException {
        MessageQueueInfo messageQueueInfo = messageQueueInfoMapper.getMessageQueueInfoByName(publishMessageInfo.getMessageName());
        if (messageQueueInfo != null) {
            String publishPassword = publishMessageInfo.getPublishPassword();
            if (!TextUtils.isEmpty(publishPassword)) {
                if (!EncryptUtils.match(publishPassword, messageQueueInfo.getPublishPassword())) {
                    throw new BusinessException("Publish password is incorrect");
                }
            }
            MessageLog messageLog = new MessageLog();
            messageLog.setMessageQueueName(publishMessageInfo.getMessageName());
            messageLog.setMessageRaw(publishMessageInfo.getMessageBody());
            messageLog.setMessageQueueInfoId(messageQueueInfo.getId());
            messageLog.setMessageStatus(MessageStatus.RECEIVED);
            messageLog.setActive(true);
            messageLog.setInUser(Const.IN_USER_NAME);
            messageLog.setInDate(new Date());
            messageLog.setLastEditUser(Const.IN_USER_NAME);
            messageLog.setLastEditDate(new Date());
            messageLogMapper.saveMessageLog(messageLog);
        } else {
            throw new BusinessException(String.format("Message with name '%1$s' not exists", publishMessageInfo.getMessageName()));
        }
    }
}
