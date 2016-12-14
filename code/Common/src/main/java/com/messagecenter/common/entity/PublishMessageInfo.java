package com.messagecenter.common.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/13.
 */
@Data
public class PublishMessageInfo implements Serializable {
    @NotBlank(message = "Message Name is required")
    @Length(min = 1, max = 50, message = "Length of Message queue name must between 1 to 50 characters")
    public String messageName;
    @NotBlank(message = "Message Body is required")
    public String messageBody;
    public String contentType;
    public String publishPassword;
}
