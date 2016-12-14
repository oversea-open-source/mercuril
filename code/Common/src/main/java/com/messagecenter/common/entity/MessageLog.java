package com.messagecenter.common.entity;

import com.messagecenter.common.entity.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class MessageLog extends BaseEntity implements Serializable {
    private int id;
    private int messageQueueInfoId;
    private String messageRaw;
    private int messageStatus;
    private String failedReason;
    private int failedRetryCount;
    private Date lastReplayFinishedDate;

    /**
     * extend
     */
    private String messageQueueName;
}
