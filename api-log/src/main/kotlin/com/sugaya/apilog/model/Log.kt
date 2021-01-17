package com.sugaya.apilog.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*

@Document
data class Log (
        @Id
        val id: UUID = UUID.randomUUID(),
        val app: String,
        val description: String,
        val createdDate: LocalDateTime = LocalDateTime.now()
)