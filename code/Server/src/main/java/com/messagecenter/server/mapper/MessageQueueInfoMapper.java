package com.messagecenter.server.mapper;

import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageQueueInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Jared on 16/12/13.
 */
@Mapper
public interface MessageQueueInfoMapper {
    MessageQueueInfo getMessageQueueInfoByName(String messageQueueName);
}
