package com.messagecenter.client.mapper;

import com.messagecenter.common.entity.MessageQueueInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Jared on 16/12/19.
 */
@Mapper
public interface MessageQueueInfoMapper {
    List<String> getMessageQueueInfoList();
}
