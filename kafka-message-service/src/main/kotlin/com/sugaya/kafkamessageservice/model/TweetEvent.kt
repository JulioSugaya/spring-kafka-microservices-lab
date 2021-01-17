package com.sugaya.kafkamessageservice.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "tweet")
data class Tweet (
    @Id
    val id: String,
    val text: String,
    val created_at: String,
) {
    companion object
}

class TweetStreamEvent (
    val data: Tweet,
    val matching_rules: Array<MatchingRule>
) {
    companion object
}

data class MatchingRule (
    val id: BigDecimal,
    val tag: String? = null
)