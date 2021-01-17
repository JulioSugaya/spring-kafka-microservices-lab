package com.sugaya.kafkamessageservice.service

import com.sugaya.kafkamessageservice.model.Tweet
import com.sugaya.kafkamessageservice.repository.TwitterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TwitterService(
    @Autowired
    private val twitterRepository: TwitterRepository
) {
    fun save(tweet: Tweet): Mono<Tweet> {
        return twitterRepository.save(tweet)
    }
}