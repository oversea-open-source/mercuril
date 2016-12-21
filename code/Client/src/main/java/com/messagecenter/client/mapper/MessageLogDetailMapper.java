package com.messagecenter.client.mapper;

import com.messagecenter.common.entity.MessageLogDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by Jared on 16/12/21.
 */
@Mapper
public interface MessageLogDetailMapper {
    void saveMessageLogDetail(MessageLogDetail messageLogDetail);

    void updateMessageLogDetail(MessageLogDetail messageLogDetail);
}
