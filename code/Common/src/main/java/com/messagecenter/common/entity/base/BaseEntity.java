package com.messagecenter.common.entity.base;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jared on 16/12/5.
 */
@Data
public class BaseEntity implements Serializable {

    @Getter(AccessLevel.NONE)
    private boolean isActive;
    private String inUser;
    private Date inDate;
    private String lastEditUser;
    private Date lastEditDate;

    public boolean getIsActive() {
        return isActive;
    }

    public String getFormattedLastEditDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        return simpleDateFormat.format(getLastEditDate());
    }

    public String getFormattedInDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        return simpleDateFormat.format(getInDate());
    }
}
