package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
public class MessageQueueSubscriber extends BaseEntity implements Serializable {

    private int id;
    private int messageQueueId;
    private String subscriberApiUrl;
    private int retryCount;
    private boolean isAutoReplay;
    private int autoReplayInterval;
    private String failedNotifyEmail;
    private boolean isActive;
    private Date inDate;
    private String lastEditUser;
    private Date lastEditDate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMessageQueueId() {
        return messageQueueId;
    }

    public void setMessageQueueId(int messageQueueId) {
        this.messageQueueId = messageQueueId;
    }

    public String getSubscriberApiUrl() {
        return subscriberApiUrl;
    }

    public void setSubscriberApiUrl(String subscriberApiUrl) {
        this.subscriberApiUrl = subscriberApiUrl;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public boolean isAutoReplay() {
        return isAutoReplay;
    }

    public void setAutoReplay(boolean autoReplay) {
        isAutoReplay = autoReplay;
    }

    public int getAutoReplayInterval() {
        return autoReplayInterval;
    }

    public void setAutoReplayInterval(int autoReplayInterval) {
        this.autoReplayInterval = autoReplayInterval;
    }

    public String getFailedNotifyEmail() {
        return failedNotifyEmail;
    }

    public void setFailedNotifyEmail(String failedNotifyEmail) {
        this.failedNotifyEmail = failedNotifyEmail;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public Date getInDate() {
        return inDate;
    }

    @Override
    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    @Override
    public String getLastEditUser() {
        return lastEditUser;
    }

    @Override
    public void setLastEditUser(String lastEditUser) {
        this.lastEditUser = lastEditUser;
    }

    @Override
    public Date getLastEditDate() {
        return lastEditDate;
    }

    @Override
    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }
}
