package com.messagecenter.portal.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jared on 16/12/8.
 */
@Data
public class PageInfo implements Serializable {
    private int pageSize;
    private int pageNum;

    public int getPageSize() {
        if (pageSize == 0) {
            return 10;
        } else {
            return pageSize;
        }
    }

    public int getPageNum() {
        if (pageNum <= 0) {
            return 1;
        } else {
            return pageNum;
        }
    }
}
