package com.messagecenter.client.mapper;

import com.messagecenter.common.entity.MessageLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Jared on 16/12/19.
 */
@Mapper
public interface MessageLogMapper {
    void updateMessageLog(MessageLog messageLog);
}
