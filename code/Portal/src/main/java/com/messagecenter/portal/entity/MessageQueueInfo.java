package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/5.
 */
public class MessageQueueInfo extends BaseEntity implements Serializable {
    private int id;
    @NotBlank(message = "Please input message queue name")
    @Length(min = 1, max = 50, message = "Length of Message queue name must between 1 to 50 characters")
    private String messageQueueName;
    @Length(max = 500, message = "Length of Message queue name must less than 500 characters")
    private String summary;
    @Length(max = 200, message = "Length of tags must less than 200 characters")
    private String tags;
    @NotBlank(message = "Please input name of owner team")
    @Length(max = 50, message = "Length of owner team name must less than 50 characters")
    private String ownerTeamName;
    @Length(min = 6, max = 20, message = "Length of password must between 6 to 20 characters")
    private String publishPassword;
    private int maxSize;
    private int maxPendingLength;
    private boolean isOrderRequired;
    @Length(max = 100, message = "Length of contact email must less than 100 characters")
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
