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
public class MessageQueueInfo extends BaseEntity implements Serializable {
    private int id;
    @NotBlank(message = "Message queue name is required")
    @Length(min = 1, max = 50, message = "Length of Message queue name must between 1 to 50 characters")
    private String messageQueueName;
    @Length(max = 500, message = "Length of Message queue name must less than 500 characters")
    private String summary;
    @Length(max = 200, message = "Length of tags must less than 200 characters")
    private String tags;
    @NotBlank(message = "Owner team name is required")
    @Length(min = 1, max = 50, message = "Length of owner team name must less than 50 characters")
    private String ownerTeamName;
    @Length(min = 6, max = 20, message = "Length of password must between 6 to 20 characters")
    private String publishPassword;
    @Range(min=1, max = 2048, message = "Max size of message must less than 2048kb")
    private Integer maxSize;
    @Range(max = 2000, message = "Max pending count of message must less than 2000")
    private Integer maxPendingLength;
    @Getter(AccessLevel.NONE)
    private boolean isOrderRequired;
    @Length(max = 100, message = "Length of contact email must less than 100 characters")
    private String contactEmail;

    /**
     * extend
     */
    private boolean usePassword;

    public boolean getIsOrderRequired() {
        return isOrderRequired;
    }
}
