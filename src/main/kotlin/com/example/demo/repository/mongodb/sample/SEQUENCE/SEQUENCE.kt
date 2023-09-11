package com.example.demo.repository.mongodb.sample.SEQUENCE

import com.example.demo.repository.mongodb.MongoField
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "SEQUENCE")
data class SEQUENCE(
    @Id val id: String,
    @Field(MongoField.SEQUENCE) val sequence: Long,
) {
    enum class SeqType {
        /** ユーザID */
        USER_ID,
    }
}
