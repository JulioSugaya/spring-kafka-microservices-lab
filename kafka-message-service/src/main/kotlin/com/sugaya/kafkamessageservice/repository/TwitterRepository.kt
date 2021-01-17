package com.sugaya.kafkamessageservice.repository

import com.sugaya.kafkamessageservice.model.Tweet
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TwitterRepository: ReactiveMongoRepository<Tweet?, String?>