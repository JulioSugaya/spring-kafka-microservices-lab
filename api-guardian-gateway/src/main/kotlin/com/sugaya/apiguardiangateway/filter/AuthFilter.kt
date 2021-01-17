package com.sugaya.apiguardiangateway.filter

import com.sugaya.apiguardiangateway.exception.CustomResponseException
import com.sugaya.apiguardiangateway.service.LogService
import com.sugaya.apiguardiangateway.service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.onErrorReturn
import java.lang.Exception


@Component
class AuthFilter(
    @Autowired
    private val logService: LogService,
    @Autowired
    private val securityService: SecurityService
) : AbstractGatewayFilterFactory<AuthFilter.Config>(Config::class.java) {


    @Value("\${security.secret}")
    private val secret: String? = null

    private fun isAuthorizationValid(authorizationHeader: String): Boolean {

        // Logic for checking the value

//        securityService.verify(authorizationHeader)
        return true
    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus): Mono<Void> {
        val response: ServerHttpResponse = exchange.response
        response.statusCode = httpStatus
        return response.setComplete()
    }

    override fun apply(config: Config): GatewayFilter {
        return label@ GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request: ServerHttpRequest = exchange.request
            if (!request.headers.containsKey("Authorization")) {
                return@GatewayFilter onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED)
            }
            val authorizationHeader: String = request.headers["Authorization"]?.get(0) ?: ""
            if (!isAuthorizationValid(authorizationHeader)) {
                return@GatewayFilter onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED)
            }

            val modifiedRequest: ServerHttpRequest =
                exchange.request.mutate().header("secret", secret).build()

            chain.filter(exchange.mutate().request(modifiedRequest).build())
                .and(securityService.verify(authorizationHeader)
                    .onErrorResume {
                        Mono.error(ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Authorization header"))
                    }
                )
                .and(logService.write("GATEWAY", "GatewayFilter" + request.headers))
        }
    }

    class Config
}
