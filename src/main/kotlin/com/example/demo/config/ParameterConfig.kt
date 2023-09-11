package com.example.demo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class ParameterConfig {

    @Value("\${datasource.mongo.uri}")
    private lateinit var uri: String

    fun getMongoUri() = this.uri

}