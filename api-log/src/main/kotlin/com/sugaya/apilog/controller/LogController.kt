package com.sugaya.apilog.controller

import com.sugaya.apilog.controller.request.LogRequest
import com.sugaya.apilog.model.Log
import com.sugaya.apilog.repository.LogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/logs")
class LogController(
        @Autowired
        private val logRepository: LogRepository
){

    @GetMapping("/{app}")
    fun getLogsByApp(@PathVariable("app") app: String) : ResponseEntity<List<Log>>{
        return ResponseEntity.ok(logRepository.findByApp(app))
    }

    @PostMapping
    fun createLog(@RequestBody request: LogRequest): ResponseEntity<Log>{
        val log = logRepository.save(
                Log(
                        app = request.app,
                        description = request.description
                )
        )
        return ResponseEntity(log, HttpStatus.CREATED)
    }
}