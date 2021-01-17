package com.sugaya.queuepopulate.startup

import com.google.gson.JsonParser
import com.sugaya.queuepopulate.service.TwitterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class StartupApplication(
    @Autowired
    private val twitterService: TwitterService
): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
//        println("[StartApplication] Tweeter - Java Timeline running...")
//        twitterService.getTwitterJavaTimeLine()

//        val str = "{\"data\":{\"created_at\":\"2021-01-06T13:34:13.000Z\",\"id\":\"1346812316214550529\",\"text\":\"RT @lwp_: #Código de #Java - Algoritmo de Dijkstra https://t.co/zhUKuf8rdU https://t.co/uqUes6I5HX\",\"author_id\":\"917562450484047873\"},\"includes\":{\"users\":[{\"name\":\"Miguel Pereira\",\"id\":\"917562450484047873\",\"username\":\"mpereiraf86\",\"created_at\":\"2017-10-10T01:28:30.000Z\"}]},\"matching_rules\":[{\"id\":1345776197767204868,\"tag\":null}]}"
//        println(JsonParser().parse(str))

        println("[StartApplication] Tweeter - Streaming...")
        twitterService.getTwitterStreamByRules()
    }
}