package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/5.
 */
public class MessageQueueInfo extends BaseEntity implements Serializable {
    private int id;
    private String messageQueueName;
    private String summary;
    private String tags;
    private String ownerTeamName;
    private String publishPassword;
    private int maxSize;
    private int maxPendingLength;
    private boolean isOrderRequired;
    private String contactEmail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageQueueName() {
        return messageQueueName;
    }

    public void setMessageQueueName(String messageQueueName) {
        this.messageQueueName = messageQueueName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getOwnerTeamName() {
        return ownerTeamName;
    }

    public void setOwnerTeamName(String ownerTeamName) {
        this.ownerTeamName = ownerTeamName;
    }

    public String getPublishPassword() {
        return publishPassword;
    }

    public void setPublishPassword(String publishPassword) {
        this.publishPassword = publishPassword;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMaxPendingLength() {
        return maxPendingLength;
    }

    public void setMaxPendingLength(int maxPendingLength) {
        this.maxPendingLength = maxPendingLength;
    }

    public boolean isOrderRequired() {
        return isOrderRequired;
    }

    public void setOrderRequired(boolean orderRequired) {
        isOrderRequired = orderRequired;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
