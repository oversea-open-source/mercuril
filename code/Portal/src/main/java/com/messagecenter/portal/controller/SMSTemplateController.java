package com.messagecenter.portal.controller;

import com.messagecenter.portal.entity.SMSTemplateEntity;
import com.messagecenter.portal.mapper.SMSTemplateMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class SMSTemplateController {

    private static Logger logger = LoggerFactory.getLogger(SMSTemplateController.class);

    @Autowired
    private SMSTemplateMapper mapper;


    @RequestMapping(value = "/api/SMSTemplate/{id}", method = RequestMethod.GET)
    public SMSTemplateEntity getUser(@PathVariable int id) {
        logger.info("Enter getUser, id: {}. ", id);

        return mapper.getById(id);
    }
}