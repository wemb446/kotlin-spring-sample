package com.example.demo.repository.mongodb.sample.SEQUENCE

import com.example.demo.repository.mongodb.MongoField
import com.example.demo.repository.mongodb.sample.SampleMongodbRepository
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class SequenceMongodbRepository: SampleMongodbRepository<SEQUENCE>() {

    /**
     * 次の番号を採番
     */
    fun next(seqId: SEQUENCE.SeqType): Long? {
        val query = Query.query(Criteria.where(MongoField.ID).isEqualTo(seqId))
        val update = Update().inc(MongoField.SEQUENCE, 1)
        val options = FindAndModifyOptions().returnNew(true)
        return this.findAndModify(query, update, options)?.sequence
    }

}