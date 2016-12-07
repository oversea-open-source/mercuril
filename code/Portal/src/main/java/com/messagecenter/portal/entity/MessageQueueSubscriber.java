package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class MessageQueueSubscriber extends BaseEntity implements Serializable {

    private int id;
    private int messageQueueId;
    private String subscriberApiUrl;
    private int retryCount;
    @Getter(AccessLevel.NONE)
    private boolean isAutoReplay;
    private int autoReplayInterval;
    private String failedNotifyEmail;

    public boolean getIsAutoReplay() {
        return isAutoReplay;
    }
}
