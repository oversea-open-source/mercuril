package com.messagecenter.portal.mapper;

import com.messagecenter.common.entity.MessageLogDetail;
import com.messagecenter.common.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/30.
 */
@Mapper
public interface MessageLogDetailMapper {
    int getMessageLogDetailCount();

    List<MessageLogDetail> getMessageLogDetailList(PageInfoQuery<MessageLogDetail> query);
}
