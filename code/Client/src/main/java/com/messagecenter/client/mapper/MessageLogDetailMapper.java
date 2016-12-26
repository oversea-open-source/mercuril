package com.messagecenter.client.mapper;

import com.messagecenter.common.entity.MessageLogDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Jared on 16/12/21.
 */
@Mapper
public interface MessageLogDetailMapper {
    void saveMessageLogDetail(MessageLogDetail messageLogDetail);

    void updateMessageLogDetail(MessageLogDetail messageLogDetail);

    List<MessageLogDetail> getMessageLogDetail(@Param("messageStatus") int messageStatus, @Param("getNeedRetry") boolean getNeedRetry, @Param("getNeedAutoReplay") boolean getNeedAutoReplay, @Param("now") Date now);
}
