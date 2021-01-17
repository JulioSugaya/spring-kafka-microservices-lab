package com.sugaya.queuepopulate.service

import com.sugaya.queuepopulate.message.ProducerService
import com.sugaya.queuepopulate.model.TweetEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class TwitterService(
    @Autowired
    private val producerService: ProducerService
) {

    @Value("\${twitter.timeline_url}")
    private val twitterTimelineUrl: String? = null

    @Value("\${twitter.stream_url}")
    private val twitterStreamUrl: String? = null

    @Value("\${twitter.bearer_token}")
    private val authorization: String? = null

    @Value("\${twitter.topic}")
    private val topic: String? = null

    private val webClient: WebClient = WebClient.create()

    fun getTwitterStreamByRules() {
        webClient
            .get()
            .uri("$twitterStreamUrl?tweet.fields=created_at")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .bodyToFlux(String::class.java)
            .subscribe {
                println("Producer - $it")
                if (it != "") producerService.send(topic!!, it)
            }
    }

    fun getTwitterJavaTimeLine() {
        webClient
            .get()
            .uri("$twitterTimelineUrl?tweet.fields=created_at")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .bodyToFlux(TweetEvent::class.java)
            .subscribe {
                it.data.forEach {
                        tweet -> println( tweet.created_at + " - " + tweet.text)
                }
            }
    }
}