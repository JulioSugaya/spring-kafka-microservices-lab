package com.sugaya.apilog.controller.request

import java.time.LocalDateTime

class LogRequest(
    val app: String,
    val description: String,
    val createdDate: LocalDateTime = LocalDateTime.now()
)