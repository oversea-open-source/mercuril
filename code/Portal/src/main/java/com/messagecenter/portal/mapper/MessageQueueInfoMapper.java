package com.messagecenter.portal.mapper;

import com.messagecenter.common.entity.MessageQueueInfo;
import com.messagecenter.common.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/5.
 */
@Mapper
public interface MessageQueueInfoMapper {
    int getMessageQueueInfoListCount();

    List<MessageQueueInfo> getMessageQueueInfoList(PageInfoQuery<MessageQueueInfo> messageQueueInfoQuery);

    void saveMessageQueueInfo(MessageQueueInfo messageQueueInfo);

    void updateMessageQueueInfo(MessageQueueInfo messageQueueInfo);
}
