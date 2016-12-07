package com.messagecenter.service.portal;

import com.messagecenter.portal.Application;
import com.messagecenter.portal.entity.MessageQueueInfo;
import com.messagecenter.portal.mapper.MessageQueueInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PortalApplicationTests {

    @Test
    public void contextLoads() {
    }

}
