package com.sugaya.apilog.repository

import com.sugaya.apilog.model.Log
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface LogRepository : MongoRepository<Log, String> {
    fun findOneById(id: String): Log
    fun findByApp(app: String): List<Log>
}