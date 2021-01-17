package com.sugaya.kafkamessageservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KafkaMessageServiceApplication

fun main(args: Array<String>) {
	runApplication<KafkaMessageServiceApplication>(*args)
}
