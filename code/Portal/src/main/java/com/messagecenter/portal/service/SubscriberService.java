package com.messagecenter.portal.service;

import com.messagecenter.portal.controller.Const;
import com.messagecenter.portal.entity.MessageQueueSubscriber;
import com.messagecenter.portal.entity.base.PageInfoQuery;
import com.messagecenter.portal.entity.base.PageInfoResult;
import com.messagecenter.portal.exception.BusinessException;
import com.messagecenter.portal.mapper.SubscriberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/12.
 */
@Service
public class SubscriberService {
    @Autowired
    SubscriberMapper subscriberMapper;

    public PageInfoResult<MessageQueueSubscriber> getSubscriberListByMessageQueueId(Integer pageNum, Integer pageSize, Integer messageQueueID, Integer id) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 0;
        }
        if (messageQueueID == null) {
            messageQueueID = 0;
        }
        if (id == null) {
            id = 0;
        }
        PageInfoQuery<MessageQueueSubscriber> pageInfoQuery = new PageInfoQuery<>();
        if (messageQueueID != 0) {
            MessageQueueSubscriber query = new MessageQueueSubscriber();
            query.setMessageQueueId(messageQueueID);
            if (id != 0) {
                query.setId(id);
            }
            pageInfoQuery.setQuery(query);
        }
        pageInfoQuery.setPageNum(pageNum);
        pageInfoQuery.setPageSize(pageSize);

        List<MessageQueueSubscriber> list = subscriberMapper.getSubscriberList(pageInfoQuery);
        PageInfoResult<MessageQueueSubscriber> result = new PageInfoResult<>();
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setList(list);
        result.setTotalCount(subscriberMapper.getSubscriberCountByMessageQueueID(messageQueueID));
        return result;
    }

    public void saveSubscriber(MessageQueueSubscriber subscriber) throws BusinessException {
        PageInfoQuery<MessageQueueSubscriber> pageInfoQuery = new PageInfoQuery<>();
        MessageQueueSubscriber query = new MessageQueueSubscriber();
        query.setSubscriberApiUrl(subscriber.getSubscriberApiUrl());
        pageInfoQuery.setQuery(query);
        List<MessageQueueSubscriber> existInfos = subscriberMapper.getSubscriberList(pageInfoQuery);

        if (existInfos == null || existInfos.size() == 0) {
            subscriber.setInUser(Const.IN_USER_NAME);
            subscriber.setInDate(new Date());
            subscriber.setLastEditDate(new Date());
            subscriber.setLastEditUser(Const.IN_USER_NAME);
            subscriber.setActive(true);
            subscriberMapper.saveSubscriber(subscriber);
        } else {
            throw new BusinessException("Subscriber with same API URL already exists");
        }
    }
}
