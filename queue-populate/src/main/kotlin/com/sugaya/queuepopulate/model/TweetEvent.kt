package com.sugaya.queuepopulate.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import java.time.LocalDateTime

class TweetEvent (
    val data: Array<Tweet>,
    val meta: Meta
)

class Meta (
    val oldest_id: String,
    val newest_id: String,
    val result_count: String,
    val next_token: String
)

class Tweet (
    val id: String,
    val text: String,
    val created_at: String,
//    @JsonIgnore
//    val createdDate: LocalDateTime = LocalDateTime.now()
)

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