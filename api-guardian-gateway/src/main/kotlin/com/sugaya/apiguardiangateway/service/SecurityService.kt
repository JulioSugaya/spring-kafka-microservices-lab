package com.sugaya.apiguardiangateway.service

import com.sugaya.apiguardiangateway.exception.CustomResponseException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class SecurityService(
    @Autowired
    private val logService: LogService
) {

    @Value("\${service.security.url}")
    private val securityUrl: String? = null

    @Value("\${service.security.app}")
    private val securityAppName: String? = null

    private val webClient: WebClient = WebClient.create()

    fun verify(authorization : String) =
        webClient
            .post()
            .uri("$securityUrl/verify")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header(HttpHeaders.AUTHORIZATION, authorization)
            .retrieve()
            .bodyToMono(String::class.java)
            .and(logService.write(securityAppName!!, "Token Verify: $authorization"))
}