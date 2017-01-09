package com.messagecenter.server.mapper;

import com.messagecenter.common.entity.MessageLog;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Jared on 16/12/14.
 */
@Mapper
public interface MessageLogMapper {
    void saveMessageLog(MessageLog messageLog);

    void updateMessageLog(MessageLog messageLog);

    List<MessageLog> getMessageLogNeedRetry(@Param("sendToMQFailedStatus") int sendToMQFailedStatus, @Param("maxRetryCount") int maxRetryCount);

    List<MessageLog> getMessageLog(MessageLog query);
}
