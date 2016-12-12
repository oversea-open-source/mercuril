package com.messagecenter.portal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class IndexController {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/")
    public ModelAndView mainPage() {
        return new ModelAndView("forward://dist/index.html");
    }
}