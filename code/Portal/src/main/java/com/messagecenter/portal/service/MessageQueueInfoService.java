package com.messagecenter.portal.service;

import com.messagecenter.portal.config.Const;
import com.messagecenter.common.entity.MessageQueueInfo;
import com.messagecenter.common.entity.base.PageInfoQuery;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.portal.exception.BusinessException;
import com.messagecenter.portal.mapper.MessageQueueInfoMapper;
import com.messagecenter.portal.utils.EncryptUtils;
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
        PageInfoQuery<MessageQueueInfo> query = new PageInfoQuery<>();
        MessageQueueInfo queueInfo = new MessageQueueInfo();
        queueInfo.setMessageQueueName(messageQueueInfo.getMessageQueueName());
        query.setQuery(queueInfo);
        List<MessageQueueInfo> existInfos = messageQueueInfoMapper.getMessageQueueInfoList(query);

        if (existInfos == null || existInfos.size() == 0) {
            messageQueueInfo.setInUser(Const.IN_USER_NAME);
            messageQueueInfo.setInDate(new Date());
            messageQueueInfo.setLastEditDate(new Date());
            messageQueueInfo.setLastEditUser(Const.IN_USER_NAME);
            messageQueueInfo.setActive(true);
            if (null != messageQueueInfo.getPublishPassword() && messageQueueInfo.getPublishPassword().length() > 0) {
                messageQueueInfo.setPublishPassword(EncryptUtils.encrypt(messageQueueInfo.getPublishPassword()));
            }
            messageQueueInfoMapper.saveMessageQueueInfo(messageQueueInfo);
        } else {
            throw new BusinessException("Message queue with same name already exists");
        }
    }

    public void updateMessageQueueInfo(MessageQueueInfo messageQueueInfo) throws BusinessException {
        PageInfoQuery<MessageQueueInfo> query = new PageInfoQuery<>();
        MessageQueueInfo queueInfo = new MessageQueueInfo();
        queueInfo.setId(messageQueueInfo.getId());
        query.setQuery(queueInfo);
        List<MessageQueueInfo> existInfos = messageQueueInfoMapper.getMessageQueueInfoList(query);

        if (existInfos != null && existInfos.size() > 0) {
            MessageQueueInfo existInfo = existInfos.get(0);
            messageQueueInfo.setInUser(existInfo.getInUser());
            messageQueueInfo.setInDate(existInfo.getInDate());
            messageQueueInfo.setLastEditDate(new Date());
            messageQueueInfo.setLastEditUser(Const.IN_USER_NAME);
            messageQueueInfo.setActive(true);
            if (messageQueueInfo.isUsePassword()) {
                if (null != messageQueueInfo.getPublishPassword() && messageQueueInfo.getPublishPassword().length() > 0) {
                    messageQueueInfo.setPublishPassword(EncryptUtils.encrypt(messageQueueInfo.getPublishPassword()));
                } else {
                    messageQueueInfo.setPublishPassword(existInfo.getPublishPassword());
                }
            } else {
                messageQueueInfo.setPublishPassword(null);
            }
            messageQueueInfoMapper.updateMessageQueueInfo(messageQueueInfo);
        } else {
            throw new BusinessException("Message queue not exists");
        }
    }

    public PageInfoResult<MessageQueueInfo> getMessageQueueInfoList(Integer pageNum, Integer pageSize, Integer id) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 0;
        }

        PageInfoQuery<MessageQueueInfo> query = new PageInfoQuery<>();
        query.setPageNum(pageNum);
        query.setPageSize(pageSize);
        if (id != null && id != 0) {
            MessageQueueInfo info = new MessageQueueInfo();
            info.setId(id);
            query.setQuery(info);
        }
        List<MessageQueueInfo> list = messageQueueInfoMapper.getMessageQueueInfoList(query);
        for (MessageQueueInfo queueInfo : list) {
            queueInfo.setUsePassword(queueInfo.getPublishPassword() != null && !"".equals(queueInfo.getPublishPassword()));
            queueInfo.setPublishPassword(null);
        }
        PageInfoResult<MessageQueueInfo> result = new PageInfoResult<>();
        result.setList(list);
        result.setTotalCount(messageQueueInfoMapper.getMessageQueueInfoListCount());
        return result;
    }
}
