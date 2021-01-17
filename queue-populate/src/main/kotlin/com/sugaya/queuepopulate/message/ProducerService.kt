package com.sugaya.queuepopulate.message

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.kafka.core.KafkaTemplate
import java.util.*


@Component
class ProducerService(
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, Any>? = null
) {

    fun send(topic: String, bag: Any) {
        val key: String = UUID.randomUUID().toString()
        println("$topic - $key - ${bag.toString()}")
        kafkaTemplate!!.send(topic, key, bag)
    }
}