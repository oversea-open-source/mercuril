package com.messagecenter.client.service;

import org.springframework.stereotype.Component;

/**
 * Created by Jared on 16/12/19.
 */
@Component
public class ReceiveService {
    public void received(String msg) {
        System.out.print("received: " + msg);
    }
}
