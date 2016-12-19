package com.messagecenter.client.mapper;

import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/5.
 */
@Mapper
public interface SubscriberMapper {

    List<MessageQueueSubscriber> getSubscriberList(int messageQueueId);

}