package com.sugaya.kafkamessageservice.service

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.sugaya.kafkamessageservice.model.Tweet
import com.sugaya.kafkamessageservice.model.TweetStreamEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ConsumerService(
    @Autowired
    private val twitterService: TwitterService
) {
    @KafkaListener(topics = ["\${twitter.topic}"], groupId = "\${spring.kafka.consumer.group-id}")
    fun consumer(bag: String) {
        val tweet: Tweet = Gson()
            .fromJson(
                JsonParser().parse(bag), TweetStreamEvent::class.java
            ).data
        println("Consumer - $tweet")
        twitterService.save(tweet).subscribe()
    }
}