package com.messagecenter.portal.service;

import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.base.PageInfoQuery;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.portal.mapper.MessageLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jared on 16/12/13.
 */
@Service
public class MessageLogService {

    @Autowired
    MessageLogMapper messageLogMapper;

    public PageInfoResult<MessageLog> getLogList(Integer pageNum, Integer pageSize, String subscriberApiUrl, String messageQueueName) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = 0;
        }

        MessageLog query = new MessageLog();
        query.setSubscriberApiUrl(subscriberApiUrl);
        query.setMessageQueueName(messageQueueName);

        PageInfoQuery<MessageLog> pageInfoQuery = new PageInfoQuery<>();
        pageInfoQuery.setQuery(query);
        pageInfoQuery.setPageNum(pageNum);
        pageInfoQuery.setPageSize(pageSize);

        List<MessageLog> list = messageLogMapper.getLogList(pageInfoQuery);
        PageInfoResult<MessageLog> result = new PageInfoResult<>();
        result.setList(list);
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setTotalCount(messageLogMapper.getLogCount());

        return result;
    }
}
