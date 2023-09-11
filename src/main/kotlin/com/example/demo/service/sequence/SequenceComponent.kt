package com.example.demo.service.sequence

import com.example.demo.repository.mongodb.sample.SEQUENCE.SEQUENCE
import com.example.demo.repository.mongodb.sample.SEQUENCE.SequenceMongodbRepository
import org.springframework.stereotype.Component

@Component
class SequenceComponent(
    private val sequenceMongodbRepository: SequenceMongodbRepository,
) {

    fun getNextId(seqId: SEQUENCE.SeqType): Long? {
        return sequenceMongodbRepository.next(seqId)
    }

}