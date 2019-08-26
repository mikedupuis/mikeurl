package com.mikedupuis.weeurl.service;

import com.mikedupuis.weeurl.repository.WeeUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ShortUrlGeneratorService {
    private WeeUrlRepository weeUrlRepository;

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

    @Autowired
    public ShortUrlGeneratorService(WeeUrlRepository weeUrlRepository) {
        this.weeUrlRepository = weeUrlRepository;
    }

    private boolean isShortUrlUsed(String shortUrl) {
        return weeUrlRepository.findByShortUrl(shortUrl).size() > 0;
    }

    public static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }

        return sb.toString();
    }

    public String generateShortUrl(String requestedShortUrl) {
        if (!isShortUrlUsed(requestedShortUrl)) {
            return requestedShortUrl;
        }

        String shortUrl;
        do {
            shortUrl = generateRandomChars(ALLOWED_CHARS, 6);
        } while (isShortUrlUsed(shortUrl));

        return shortUrl;
    }
}
