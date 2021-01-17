package com.sugaya.apilog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiLogApplication

fun main(args: Array<String>) {
	runApplication<ApiLogApplication>(*args)
}
