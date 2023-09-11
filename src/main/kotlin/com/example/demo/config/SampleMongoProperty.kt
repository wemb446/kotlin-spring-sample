package com.example.demo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
class SampleMongoProperty(
    private val parameterConfig: ParameterConfig,
): AbstractMongoConfig() {

    override fun mongoProperty(): MongoProperty {
        val mongoProperty = MongoProperty()
        mongoProperty.uri = parameterConfig.getMongoUri()
        return mongoProperty
    }

    @Primary
    @Bean
    fun sampleMongoDbFactory(): MongoDatabaseFactory {
        return SimpleMongoClientDatabaseFactory(this.mongoClient(), this.databaseName)
    }

    @Bean
    fun sampleMongoTemplate(converter: MappingMongoConverter): MongoTemplate {
        return MongoTemplate(
            this.sampleMongoDbFactory(),
            mappingMongoConverter(
                sampleMongoDbFactory(),
                customConversions(),
                mongoMappingContext(customConversions(), mongoManagedTypes())
            )
        )
    }

}