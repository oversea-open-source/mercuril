package com.messagecenter.common.entity;

/**
 * Created by Jared on 16/12/14.
 */
public class MessageStatus {
    /**
     * Message Log
     */
    public static final int RECEIVED = 0;
    public static final int SENT_TO_MQ_SUCCESS = 1;
    public static final int SENT_TO_MQ_FAILED = 2;
    public static final int SENDER_RECEIVED = 3;
    /**
     * Message Log Detail
     */
    public static final int SENT_TO_SUB_SUCCESS = 4;
    public static final int SENT_TO_SUB_FAILED = 5;
}
