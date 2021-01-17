package com.sugaya.apiguardiangateway.service

import com.sugaya.apiguardiangateway.model.LogRequest
import com.sugaya.apiguardiangateway.model.LogResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class LogService {

    @Value("\${service.logs.url}")
    private val logUrl: String? = null

    private val webClient: WebClient = WebClient.create()

    fun write(app : String, description : String) : Mono<LogResponse>{
        return webClient
            .post()
            .uri(logUrl!!)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .body(Mono.just(LogRequest(app, description)), LogRequest::class.java)
            .retrieve()
            .bodyToMono(LogResponse::class.java)
    }
}