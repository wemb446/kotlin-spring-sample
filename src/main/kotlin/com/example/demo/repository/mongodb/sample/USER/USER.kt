package com.example.demo.repository.mongodb.sample.USER

import com.example.demo.repository.mongodb.MongoField
import com.example.demo.utility.DateUtils
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document(collection = "USER")
data class USER(
    @Id var userId: Long = 0L,
    @Field(MongoField.CREATED_AT) val createdAt: LocalDateTime = DateUtils.DEFAULT_LOCAL_DATETIME,
    @Field(MongoField.UPDATED_AT) val updatedAt: LocalDateTime = DateUtils.DEFAULT_LOCAL_DATETIME,
    @Field(MongoField.USER_NAME) val userName: String,
)
