package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
public class MessageLog extends BaseEntity implements Serializable {
    private int id;
    private int messageQueueSubscriberId;
    private String messageRaw;
    private int messageStatus;
    private String failedReason;
    private int failedRetryCount;
    private Date lastReplayFinishedDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageQueueSubscriberId() {
        return messageQueueSubscriberId;
    }

    public void setMessageQueueSubscriberId(int messageQueueSubscriberId) {
        this.messageQueueSubscriberId = messageQueueSubscriberId;
    }

    public String getMessageRaw() {
        return messageRaw;
    }

    public void setMessageRaw(String messageRaw) {
        this.messageRaw = messageRaw;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getFailedReason() {
        return failedReason;
    }

    public void setFailedReason(String failedReason) {
        this.failedReason = failedReason;
    }

    public int getFailedRetryCount() {
        return failedRetryCount;
    }

    public void setFailedRetryCount(int failedRetryCount) {
        this.failedRetryCount = failedRetryCount;
    }

    public Date getLastReplayFinishedDate() {
        return lastReplayFinishedDate;
    }

    public void setLastReplayFinishedDate(Date lastReplayFinishedDate) {
        this.lastReplayFinishedDate = lastReplayFinishedDate;
    }
}
