package com.sugaya.apiguardiangateway.exception

import org.springframework.http.HttpStatus
import java.lang.RuntimeException
import java.util.*

class CustomResponseException : RuntimeException{
    //
    var status: HttpStatus? = null
    override var message: String? = null
    var errors: List<String>? = null

    //
    constructor() : super() {}
    constructor(status: HttpStatus?, message: String?, errors: List<String>?) : super() {
        this.status = status
        this.message = message
        this.errors = errors
    }

    constructor(status: HttpStatus?, message: String?, error: String?) : super() {
        this.status = status
        this.message = message
        errors = Arrays.asList(error) as List<String>?
    }

    fun setError(error: String?) {
        errors = Arrays.asList(error) as List<String>?
    }
}