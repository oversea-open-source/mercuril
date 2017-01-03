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

    public static String getMessageStatusDescription(int messageStatus) {
        String messageStatusDescription;
        switch (messageStatus) {
            case RECEIVED:
                messageStatusDescription = "Received";
                break;
            case SENT_TO_MQ_SUCCESS:
                messageStatusDescription = "Sent to MQ Success";
                break;
            case SENT_TO_MQ_FAILED:
                messageStatusDescription = "Sent to MQ Failed";
                break;
            case SENDER_RECEIVED:
                messageStatusDescription = "Sender Received";
                break;
            case SENT_TO_SUB_SUCCESS:
                messageStatusDescription = "Sent to Subscriber Success";
                break;
            case SENT_TO_SUB_FAILED:
                messageStatusDescription = "Sent to Subscriber Failed";
                break;
            default:
                messageStatusDescription = "Unknown status";
        }

        return messageStatusDescription;
    }
}
