package com.messagecenter.common.entity;

import com.messagecenter.common.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class MessageLogDetail extends BaseEntity implements Serializable {
    private int id;
    private int messageLogId;
    private int messageQueueSubscriberId;
    private int messageStatus;
    private String failedReason;
    private int failedRetryCount;
    private Date lastReplayFinishedDate;

    /**
     * extend
     */
    private MessageLog messageLog;
    private MessageQueueSubscriber messageQueueSubscriber;

    @Getter(AccessLevel.NONE)
    private String messageStatusDescription;

    public String getMessageStatusDescription() {
        return MessageStatus.getMessageStatusDescription(messageStatus);
    }
}
