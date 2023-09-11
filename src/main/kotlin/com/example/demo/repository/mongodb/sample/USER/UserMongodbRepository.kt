package com.example.demo.repository.mongodb.sample.USER

import com.example.demo.repository.mongodb.MongoField
import com.example.demo.repository.mongodb.sample.SampleMongodbRepository
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class UserMongodbRepository: SampleMongodbRepository<USER>() {

    /**
     * USER ドキュメントを 1 件取得
     */
    fun fetchByUserId(userId: Long): USER? {
        val query = Query()
            .addCriteria(Criteria.where(MongoField.ID).isEqualTo(userId))
        return this.findOne(query)
    }

    /**
     * USER ドキュメントを一覧取得
     */
    fun fetchAll(): List<USER> {
        return this.findAll()
    }

    /**
     * USER ドキュメントを 1 件追加
     */
    fun insertOne(user: USER): USER {
        return this.insert(user)
    }

    /**
     * USER ドキュメントを 1 件更新
     */
    fun updateOne(user: USER): USER {
        return this.save(user)
    }

    /**
     * USER ドキュメントを 1 件削除
     */
    fun deleteOne(userId: Long): USER? {
        return this.findAndRemove(Query.query(Criteria.where(MongoField.ID).isEqualTo(userId)))
    }

}