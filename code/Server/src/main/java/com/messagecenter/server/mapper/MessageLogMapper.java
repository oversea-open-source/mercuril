package com.messagecenter.server.mapper;

import com.messagecenter.common.entity.MessageLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Jared on 16/12/14.
 */
@Mapper
public interface MessageLogMapper {
    void saveMessageLog(MessageLog messageLog);
    void updateMessageLog(MessageLog messageLog);
}
