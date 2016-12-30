package com.messagecenter.portal.service;

import com.messagecenter.common.config.Const;
import com.messagecenter.common.entity.MessageLogDetail;
import com.messagecenter.common.entity.base.PageInfoQuery;
import com.messagecenter.common.entity.base.PageInfoResult;
import com.messagecenter.portal.mapper.MessageLogDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jared on 16/12/30.
 */
@Service
public class MessageLogDetailService {
    @Autowired
    MessageLogDetailMapper messageLogDetailMapper;

    public PageInfoResult<MessageLogDetail> getMessageLogDetailList(Integer pageNum, Integer pageSize, Integer messageLogId) {
        if (pageNum == null) {
            pageNum = 0;
        }
        if (pageSize == null) {
            pageSize = Const.DEFAULT_PAGE_SIZE;
        }

        PageInfoQuery<MessageLogDetail> pageInfoQuery = new PageInfoQuery<>();
        pageInfoQuery.setPageSize(pageSize);
        pageInfoQuery.setPageNum(pageNum);

        if (messageLogId != null && messageLogId != 0) {
            MessageLogDetail query = new MessageLogDetail();
            query.setMessageLogId(messageLogId);
            pageInfoQuery.setQuery(query);
        }

        List<MessageLogDetail> messageLogDetails = messageLogDetailMapper.getMessageLogDetailList(pageInfoQuery);
        PageInfoResult<MessageLogDetail> result = new PageInfoResult<>();
        result.setList(messageLogDetails);
        result.setPageSize(pageSize);
        result.setPageNum(pageNum);
        result.setTotalCount(messageLogDetailMapper.getMessageLogDetailCount());
        return result;
    }
}
