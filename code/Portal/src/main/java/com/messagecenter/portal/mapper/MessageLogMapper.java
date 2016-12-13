package com.messagecenter.portal.mapper;

import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/13.
 */
@Mapper
public interface MessageLogMapper {
    int getLogCount();

    List<MessageLog> getLogList(PageInfoQuery<MessageLog> query);
}
