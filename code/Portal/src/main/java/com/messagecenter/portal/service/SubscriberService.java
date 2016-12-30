package com.messagecenter.portal.service;

import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.base.PageInfoQuery;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.common.exception.BusinessException;
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
            pageSize = Const.DEFAULT_PAGE_SIZE;
        }
        if (messageQueueID == null) {
            messageQueueID = 0;
        }
        if (id == null) {
            id = 0;
        }
        PageInfoQuery<MessageQueueSubscriber> pageInfoQuery = new PageInfoQuery<>();
        if (messageQueueID != 0 || id != 0) {
            MessageQueueSubscriber query = new MessageQueueSubscriber();
            if (messageQueueID != 0) {
                query.setMessageQueueId(messageQueueID);
            }
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

    public void updateSubscriber(MessageQueueSubscriber subscriber) throws BusinessException {

        PageInfoQuery<MessageQueueSubscriber> pageInfoQuery = new PageInfoQuery<>();
        MessageQueueSubscriber query = new MessageQueueSubscriber();
        query.setId(subscriber.getId());
        pageInfoQuery.setQuery(query);
        List<MessageQueueSubscriber> existInfos = subscriberMapper.getSubscriberList(pageInfoQuery);

        if (existInfos != null && existInfos.size() > 0) {
            MessageQueueSubscriber sameUrlQuery = new MessageQueueSubscriber();
            sameUrlQuery.setId(subscriber.getId());
            sameUrlQuery.setSubscriberApiUrl(subscriber.getSubscriberApiUrl());
            int sameUrlCount = subscriberMapper.getSameUrlSubscriberCount(sameUrlQuery);

            if (sameUrlCount == 0) {
                MessageQueueSubscriber existInfo = existInfos.get(0);
                subscriber.setInDate(existInfo.getInDate());
                subscriber.setInUser(existInfo.getInUser());
                subscriber.setLastEditUser(Const.IN_USER_NAME);
                subscriber.setLastEditDate(new Date());
                subscriber.setActive(true);
                subscriberMapper.updateSubscriber(subscriber);
            } else {
                throw new BusinessException("Subscriber with same API URL already exists");
            }
        } else {
            throw new BusinessException("Subscriber not exists");
        }


    }
}
