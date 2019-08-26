package com.mikedupuis.weeurl.controller;

import com.mikedupuis.weeurl.dto.WeeurlCreateRequest;
import com.mikedupuis.weeurl.entity.WeeUrl;
import com.mikedupuis.weeurl.service.WeeUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class ShortUrlController {
    WeeUrlService weeUrlService;
    Logger logger = LoggerFactory.getLogger(ShortUrlController.class);

    @Autowired
    public ShortUrlController(WeeUrlService weeUrlService) {
        this.weeUrlService = weeUrlService;
    }

    @GetMapping("weeurl/{key}")
    public void redirect(@PathVariable String key, HttpServletResponse httpServletResponse) {
        WeeUrl weeUrl;
        logger.info("Redirecting for key " + key);
        try {
            weeUrl = weeUrlService.fetchShortUrl(key);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        logger.info("Got full URL " + weeUrl.getFullUrl());

        httpServletResponse.setHeader("Location", weeUrl.getFullUrl());
        httpServletResponse.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY); // SC_MOVED_PERMANENTLY?
    }

    @PostMapping(value = "weeurl", consumes = "application/json")
    public void create(@RequestBody WeeurlCreateRequest request, HttpServletResponse httpServletResponse) {
        try {
            weeUrlService.create(request);
            httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
