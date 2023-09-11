package com.example.demo.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ReadPreference
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.management.JMXConnectionPoolListener
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import java.util.concurrent.TimeUnit

abstract class AbstractMongoConfig: AbstractMongoClientConfiguration() {

    abstract fun mongoProperty(): MongoProperty

    override fun mongoClient(): MongoClient {

        val mongoProperty = this.mongoProperty()

        val mongoClientSetting = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(mongoProperty.uri))
            .applyToConnectionPoolSettings {
                it.addConnectionPoolListener(JMXConnectionPoolListener())
                    .minSize(mongoProperty.minConnectionPerHost)
                    .maxSize(mongoProperty.connectionPerHost)
                    .maxWaitTime(mongoProperty.maxWaitTime, TimeUnit.MILLISECONDS)
                    .maxConnectionIdleTime(mongoProperty.maxConnectionIdleTime, TimeUnit.MILLISECONDS)
            }
            .applyToClusterSettings {
                it.serverSelectionTimeout(mongoProperty.serverSelectionTimeout, TimeUnit.MILLISECONDS)
            }
            .applyToSocketSettings {
                it.connectTimeout(mongoProperty.connectionTimeout, TimeUnit.MILLISECONDS)
                    .readTimeout(mongoProperty.socketTimeout, TimeUnit.MILLISECONDS)
            }
            .readPreference(ReadPreference.valueOf(mongoProperty.readPreference))
            .writeConcern(WriteConcern.valueOf(mongoProperty.writeConcern))
            .build()

        return MongoClients.create(mongoClientSetting)
    }

    override fun getDatabaseName(): String {
        return this.mongoProperty().mongoClientDatabase
    }

    /**
     * insert する document に `_class` field を付与しないために override
     */
    override fun mappingMongoConverter(
        databaseFactory: MongoDatabaseFactory,
        customConversions: MongoCustomConversions,
        mappingContext: MongoMappingContext
    ): MappingMongoConverter {
        val mappingMongoConverter = super.mappingMongoConverter(databaseFactory, customConversions, mappingContext)
        mappingMongoConverter.setTypeMapper(
            DefaultMongoTypeMapper(null)
        )
        return mappingMongoConverter
    }

    override fun autoIndexCreation(): Boolean {
        return true
    }

}