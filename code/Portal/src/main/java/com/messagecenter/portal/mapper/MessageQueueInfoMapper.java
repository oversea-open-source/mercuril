package com.messagecenter.portal.mapper;

import com.messagecenter.portal.entity.MessageQueueInfo;
import com.messagecenter.portal.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/5.
 */
@Mapper
public interface MessageQueueInfoMapper {
    List<MessageQueueInfo> getMessageQueueInfoList(PageInfoQuery<MessageQueueInfo> messageQueueInfoQuery);

    void saveMessageQueueInfoList(MessageQueueInfo messageQueueInfo);
}
