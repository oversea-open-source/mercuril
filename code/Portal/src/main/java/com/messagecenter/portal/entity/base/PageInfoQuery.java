package com.messagecenter.portal.entity.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Jared on 16/12/8.
 */
@Data
public class PageInfoQuery<T> extends PageInfo implements Serializable {
    private T query;
    private int start;

    public int getStart() {
        return (getPageNum() - 1) * getPageSize();
    }
}
