package com.messagecenter.common.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Jared on 16/12/8.
 */
@Data
public class PageInfoResult<T> extends PageInfo implements Serializable {
    private List<T> list;
}
