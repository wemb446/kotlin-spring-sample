package com.example.demo.repository.mongodb.sample.TWEET

import com.example.demo.repository.mongodb.MongoField
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "TWEET")
data class TWEET(
    @Id var id: String? = null,
    @Field(MongoField.USER_ID) val userId: Long,
    @Field(MongoField.TWEET_CONTENT) val content: String,
)
