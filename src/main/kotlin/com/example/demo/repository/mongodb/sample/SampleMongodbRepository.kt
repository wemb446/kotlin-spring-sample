package com.example.demo.repository.mongodb.sample

import com.example.demo.repository.mongodb.AbstractMongodbRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository

@Repository
class SampleMongodbRepository<T>: AbstractMongodbRepository<T>() {

    @Autowired
    lateinit var sampleMongoTemplate: MongoTemplate

    override fun getMongoTemplate(): MongoTemplate {
        return this.sampleMongoTemplate
    }

}