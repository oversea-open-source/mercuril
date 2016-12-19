package com.messagecenter.client.controller;

import com.messagecenter.common.entity.base.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jared on 16/12/19.
 */
@RestController
public class IndexController {
    @RequestMapping("/api/foo")
    public BaseResponse foo() {
        return new BaseResponse();
    }
}
