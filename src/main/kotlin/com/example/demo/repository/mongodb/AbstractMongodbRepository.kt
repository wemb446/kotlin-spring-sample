package com.example.demo.repository.mongodb

import com.mongodb.client.MongoCollection
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import jakarta.annotation.PostConstruct
import org.bson.Document
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.index.Index
import org.springframework.data.mongodb.core.index.IndexOperations
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class AbstractMongodbRepository<T> {

    abstract fun getMongoTemplate(): MongoTemplate
    private lateinit var clazz: Class<T>

    @PostConstruct
    @Suppress("UNCHECKED_CAST")
    private fun determineClass() {
        val parameterizedType: ParameterizedType = this.javaClass.genericSuperclass as ParameterizedType
        val actualTypeArguments: Array<Type> = parameterizedType.actualTypeArguments
        if (actualTypeArguments[0].typeName != "T") {
            this.clazz = actualTypeArguments[0] as Class<T>
        }
    }

    protected open fun findAll(): List<T> {
        return this.getMongoTemplate().findAll(this.clazz)
    }

    protected open fun findAll(collectionName: String): List<T> {
        return this.getMongoTemplate().findAll(this.clazz, collectionName)
    }

    protected open fun find(query: Query): List<T> {
        return this.getMongoTemplate().find(query, this.clazz)
    }

    protected open fun find(query: Query, collectionName: String): List<T> {
        return this.getMongoTemplate().find(query, this.clazz, collectionName)
    }

    protected open fun findById(id: ObjectId): T? {
        return this.getMongoTemplate().findById(id, this.clazz)
    }

    protected open fun findById(id: String): T? {
        return this.getMongoTemplate().findById(ObjectId(id), this.clazz)
    }

    protected open fun findOne(query: Query): T? {
        return this.getMongoTemplate().findOne(query, this.clazz)
    }

    protected open fun findOne(): T? {
        return this.getMongoTemplate().findOne(Query(), this.clazz)
    }

    protected open fun findOne(query: Query, collectionName: String): T? {
        return this.getMongoTemplate().findOne(query, this.clazz, collectionName)
    }

    protected open fun exists(query: Query): Boolean {
        return this.getMongoTemplate().exists(query, this.clazz)
    }

    protected open fun count(query: Query): Long {
        return this.getMongoTemplate().count(query, this.clazz)
    }

    protected open fun insert(objectToSave: T & Any): T {
        return this.getMongoTemplate().insert(objectToSave)
    }

    protected open fun insert(objectToSave: T & Any, collectionName: String): T {
        return this.getMongoTemplate().insert(objectToSave, collectionName)
    }

    protected open fun insertAll(batchToSave: Collection<T>): Collection<T> {
        return this.getMongoTemplate().insert(batchToSave, this.clazz)
    }

    protected open fun insertAll(batchToSave: Collection<T>, collectionName: String): Collection<T> {
        return this.getMongoTemplate().insert(batchToSave, collectionName)
    }

    protected open fun save(objectToSave: T & Any): T {
        return this.getMongoTemplate().save(objectToSave)
    }

    protected open fun save(objectToSave: T & Any, collectionName: String): T {
        return this.getMongoTemplate().save(objectToSave, collectionName)
    }

    protected open fun upsert(query: Query, update: Update): UpdateResult {
        return this.getMongoTemplate().upsert(query, update, this.clazz)
    }

    protected open fun upsert(query: Query, update: Update, collectionName: String): UpdateResult {
        return this.getMongoTemplate().upsert(query, update, this.clazz, collectionName)
    }

    protected open fun updateFirst(query: Query, update: Update): UpdateResult {
        return this.getMongoTemplate().updateFirst(query, update, this.clazz)
    }

    protected open fun updateFirst(query: Query, update: Update, collectionName: String): UpdateResult {
        return this.getMongoTemplate().updateFirst(query, update, this.clazz, collectionName)
    }

    protected open fun updateMulti(query: Query, update: Update): UpdateResult {
        return this.getMongoTemplate().updateMulti(query, update, this.clazz)
    }

    protected open fun remove(query: Query): DeleteResult {
        return this.getMongoTemplate().remove(query, this.clazz)
    }

    protected open fun remove(query: Query, collectionName: String): DeleteResult {
        return this.getMongoTemplate().remove(query, collectionName)
    }

    protected open fun remove(`object`: Any): DeleteResult {
        return this.getMongoTemplate().remove(`object`)
    }

    protected open fun remove(`object`: Any, collectionName: String): DeleteResult {
        return this.getMongoTemplate().remove(`object`, collectionName)
    }

    protected open fun collectionExists(): Boolean {
        return this.getMongoTemplate().collectionExists(this.clazz)
    }

    protected open fun collectionExists(collectionName: String): Boolean {
        return this.getMongoTemplate().collectionExists(collectionName)
    }

    protected open fun createCollection(): MongoCollection<Document> {
        return this.getMongoTemplate().createCollection(this.clazz)
    }

    protected open fun createCollection(collectionName: String): MongoCollection<Document> {
        return this.getMongoTemplate().createCollection(collectionName)
    }

    protected open fun indexOps(): IndexOperations {
        return this.getMongoTemplate().indexOps(this.clazz)
    }

    protected open fun indexOps(collectionName: String): IndexOperations {
        return this.getMongoTemplate().indexOps(collectionName)
    }

    protected open fun findAndModify(query: Query, update: Update, options: FindAndModifyOptions): T? {
        return this.getMongoTemplate().findAndModify(query, update, options, this.clazz)
    }

    protected open fun findAndModify(
        query: Query,
        update: Update,
        options: FindAndModifyOptions,
        collectionName: String
    ): T? {
        return this.getMongoTemplate().findAndModify(query, update, options, this.clazz, collectionName)
    }

    protected open fun findAndModify(query: Query, update: Update): T? {
        return this.getMongoTemplate().findAndModify(query, update, this.clazz)
    }

    protected open fun findAndModify(query: Query, update: Update, collectionName: String): T? {
        return this.getMongoTemplate().findAndModify(query, update, this.clazz, collectionName)
    }

    protected open fun findAndRemove(query: Query): T? {
        return this.getMongoTemplate().findAndRemove(query, this.clazz)
    }

    protected open fun findAndRemove(query: Query, collectionName: String): T? {
        return this.getMongoTemplate().findAndRemove(query, this.clazz, collectionName)
    }

    protected open fun createIndex(index: Index, collectionName: String) {
        val indexOperations = this.getMongoTemplate().indexOps(collectionName)
        indexOperations.ensureIndex(index.background())
    }

    protected open fun createIndex(index: Index) {
        val indexOperations = this.getMongoTemplate().indexOps(this.clazz)
        indexOperations.ensureIndex(index.background())
    }

    protected open fun createIndex(indexCollection: Collection<Index>, collectionName: String) {
        val indexOperations = this.getMongoTemplate().indexOps(collectionName)
        for (index in indexCollection) {
            indexOperations.ensureIndex(index.background())
        }
    }

    protected open fun createIndex(indexCollection: Collection<Index>) {
        val indexOperations = this.getMongoTemplate().indexOps(this.clazz)
        for (index in indexCollection) {
            indexOperations.ensureIndex(index.background())
        }
    }

}