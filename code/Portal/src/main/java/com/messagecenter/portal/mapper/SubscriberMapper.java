package com.messagecenter.portal.mapper;

import com.messagecenter.common.entity.MessageQueueSubscriber;
import com.messagecenter.common.entity.base.PageInfoQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/5.
 */
@Mapper
public interface SubscriberMapper {
    int getSubscriberCountByMessageQueueID(int messageQueueID);

    List<MessageQueueSubscriber> getSubscriberList(PageInfoQuery<MessageQueueSubscriber> subscriberQuery);

    void saveSubscriber(MessageQueueSubscriber subscriber);

    void updateSubscriber(MessageQueueSubscriber subscriber);

    int getSameUrlSubscriberCount(MessageQueueSubscriber subscriber);
}
