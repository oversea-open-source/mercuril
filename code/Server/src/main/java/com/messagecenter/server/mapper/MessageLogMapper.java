package com.messagecenter.server.mapper;

import com.messagecenter.common.entity.MessageLog;
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

    MessageLog getMessageLogById(int id);
}
