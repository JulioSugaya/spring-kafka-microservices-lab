package com.sugaya.kafkamessageservice.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration

import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoRepositories("com.sugaya.kafkamessageservice.repository")
class MongoConfiguration : AbstractReactiveMongoConfiguration() {

    @Value("\${spring.data.mongodb.host}")
    private val host: String? = null

    @Value("\${spring.data.mongodb.database}")
    private val database: String? = null

    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create("mongodb://$host")
    }

    override fun getDatabaseName(): String {
        return database!!
    }
}