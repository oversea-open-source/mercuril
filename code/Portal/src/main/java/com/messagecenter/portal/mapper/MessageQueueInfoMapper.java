package com.messagecenter.portal.mapper;

import com.messagecenter.portal.entity.MessageQueueInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/5.
 */
@Mapper
public interface MessageQueueInfoMapper {
    List<MessageQueueInfo> getMessageQueueInfoList(MessageQueueInfo messageQueueInfo);

    void saveMessageQueueInfoList(MessageQueueInfo messageQueueInfo);
}
