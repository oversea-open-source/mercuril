package com.messagecenter.client.service;

import com.messagecenter.client.mapper.MessageQueueInfoMapper;
import com.messagecenter.common.entity.MessageQueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jared on 16/12/19.
 */
@Service
public class MessageQueueInfoService {
    @Autowired
    MessageQueueInfoMapper messageQueueInfoMapper;

    public List<String> getMessageQueueNameList() {
        return messageQueueInfoMapper.getMessageQueueInfoList();
    }
}
