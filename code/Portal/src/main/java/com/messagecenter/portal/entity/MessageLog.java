package com.messagecenter.portal.entity;

import com.messagecenter.portal.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class MessageLog extends BaseEntity implements Serializable {
    private int id;
    private int messageQueueSubscriberId;
    private String messageRaw;
    private int messageStatus;
    private String failedReason;
    private int failedRetryCount;
    private Date lastReplayFinishedDate;
}
