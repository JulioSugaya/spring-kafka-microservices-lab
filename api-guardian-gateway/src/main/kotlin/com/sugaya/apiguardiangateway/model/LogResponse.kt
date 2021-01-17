package com.sugaya.apiguardiangateway.model

import java.time.LocalDateTime

class LogResponse (app : String, description : String) : LogRequest(app, description) {
        val id: String = ""
        val createdDate: String = ""
}