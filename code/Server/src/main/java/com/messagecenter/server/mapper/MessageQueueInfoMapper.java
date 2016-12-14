package com.messagecenter.server.mapper;

import com.messagecenter.common.entity.MessageLog;
import com.messagecenter.common.entity.MessageQueueInfo;

/**
 * Created by Jared on 16/12/13.
 */
public interface MessageQueueInfoMapper {
    MessageQueueInfo getMessageQueueInfoByName(String messageQueueName);
}
