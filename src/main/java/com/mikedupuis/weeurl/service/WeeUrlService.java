package com.mikedupuis.weeurl.service;

import com.mikedupuis.weeurl.dto.WeeurlCreateRequest;
import com.mikedupuis.weeurl.entity.WeeUrl;
import com.mikedupuis.weeurl.repository.WeeUrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WeeUrlService {
    Logger logger = LoggerFactory.getLogger(WeeUrlService.class);

    private WeeUrlRepository weeUrlRepository;
    private UrlValidatorService urlValidatorService;
    private ShortUrlGeneratorService shortUrlGeneratorService;

    @Autowired
    public WeeUrlService(WeeUrlRepository weeUrlRepository, UrlValidatorService urlValidatorService, ShortUrlGeneratorService shortUrlGeneratorService) {
        this.weeUrlRepository = weeUrlRepository;
        this.urlValidatorService = urlValidatorService;
        this.shortUrlGeneratorService = shortUrlGeneratorService;
    }

    public WeeUrl fetchShortUrl(String key) throws Exception {
        logger.info("Fetching short url for " + key);
        List<WeeUrl> weeUrlList = weeUrlRepository.findByShortUrl(key);

        logger.info("Got " + (weeUrlList == null ? "[NULL]" : (weeUrlList.size() + " results")));

        if (weeUrlList == null || weeUrlList.size() == 0) {
            // No such entry
            throw new NoSuchElementException("No URLs match key '" + key + "'");
        }

        if (weeUrlList.size() > 1) {
            throw new Exception("System error: Multiple URLs match key '" + key + "'");
        }

        WeeUrl weeUrl = weeUrlList.get(0);

        weeUrl.use();
        weeUrlRepository.save(weeUrl);

        return weeUrl;
    }

    public void create(WeeurlCreateRequest request) throws Exception {
        WeeUrl weeUrl = new WeeUrl();

        if (!urlValidatorService.isUrlValid(request.getUrl())) {
            throw new Exception("Invalid URL '" + request.getUrl() + "'");
        }

        weeUrl.setFullUrl(request.getUrl());
        weeUrl.setShortUrl(shortUrlGeneratorService.generateShortUrl(request.getRequestedShortUrl()));
        weeUrl.setCreated(new Date());
        weeUrl.setUsedCount(BigInteger.valueOf(0));

        weeUrlRepository.save(weeUrl);
    }
}
