package com.messagecenter.common.entity;

import com.messagecenter.common.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class MessageQueueSubscriber extends BaseEntity implements Serializable {

    private int id;
    @Range(min = 0, message = "Message queue id must larger than 0")
    private int messageQueueId;
    @NotBlank(message = "Subscriber's API URL is required")
    @Length(min = 1, max = 500, message = "Length of Subscriber's API URL must between 1 to 500 characters")
    private String subscriberApiUrl;
    @Range(min = 0, max = 20, message = "Retry count must between 0 to 20")
    private int retryCount;
    @Getter(AccessLevel.NONE)
    private boolean isAutoReplay;
    @Range(min = 5, max = 60000, message = "Auto replay interval must between 5 to 648000")
    private Integer autoReplayInterval;
    @Length(max = 100, message = "Length of failed notify email must less than 100 characters")
    private String failedNotifyEmail;

    public boolean getIsAutoReplay() {
        return isAutoReplay;
    }
}
