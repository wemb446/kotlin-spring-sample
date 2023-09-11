package com.example.demo.config

import org.springframework.boot.autoconfigure.mongo.MongoProperties

class MongoProperty: MongoProperties() {
    var connectionPerHost: Int = 10
    var minConnectionPerHost: Int = 2
    var threadsAllowedToBlockForConnectionMultiplier: Int = 10
    var connectionTimeout: Int = 30000
    var maxWaitTime: Long = 1500L
    var socketTimeout: Int = 30000
    var readPreference: String = "PRIMARY"
    var writeConcern: String = "ACKNOWLEDGED"
    var serverSelectionTimeout: Long = 50000L
    var maxConnectionIdleTime = 21600000L
}