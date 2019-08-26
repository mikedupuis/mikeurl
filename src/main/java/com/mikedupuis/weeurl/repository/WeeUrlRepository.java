package com.mikedupuis.weeurl.repository;

import com.mikedupuis.weeurl.entity.WeeUrl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeeUrlRepository extends CrudRepository<WeeUrl, Integer> {
    List<WeeUrl> findByShortUrl(String shortUrl);
}
