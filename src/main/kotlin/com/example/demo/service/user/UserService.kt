package com.example.demo.service.user

import com.example.demo.controller.user.UserIO
import com.example.demo.repository.mongodb.sample.SEQUENCE.SEQUENCE
import com.example.demo.repository.mongodb.sample.USER.USER
import com.example.demo.repository.mongodb.sample.USER.UserMongodbRepository
import com.example.demo.service.sequence.SequenceComponent
import com.example.demo.utility.DateUtils
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class UserService(
    private val sequenceComponent: SequenceComponent,
    private val userMongodbRepository: UserMongodbRepository,
) {

    /**
     * ユーザを全件取得
     */
    fun getUsers(): List<UserIO.UserForListEntity> {
        val userList: List<USER> = userMongodbRepository.fetchAll()

        return this.userModelListToUserIO(userList)
    }

    /**
     * ユーザIDからユーザを1件取得
     */
    fun getUserByUserId(userId: Long): UserIO.UserForDetailEntity {
        val user: USER = userMongodbRepository.fetchByUserId(userId)
            ?: throw Exception("Not exists the user.")
        return this.userModelToUserIO(user)
    }

    /**
     * ユーザを1件追加
     */
    fun createNewUser(userName: String): UserIO.UserForDetailEntity {
        // 次のユーザIDを採番
        val nextId: Long = sequenceComponent.getNextId(SEQUENCE.SeqType.USER_ID)
            ?: throw Exception("Unexpected error.")

        val user = USER(
            userId = nextId,
            userName = userName
        )

        userMongodbRepository.insertOne(user)

        return this.userModelToUserIO(user)
    }

    /**
     * ユーザIDを指定して1件更新
     */
    fun updateUser(userId: Long, userName: String): UserIO.UserForDetailEntity {
        val userBefore: USER = userMongodbRepository.fetchByUserId(userId)
            ?: throw Exception("Not exists the user.")

        val userAfter = USER(
            userId = userBefore.userId,
            userName = userName,
            createdAt = userBefore.createdAt,
            updatedAt = DateUtils.DEFAULT_LOCAL_DATETIME
        )

        userMongodbRepository.updateOne(userAfter)

        return this.userModelToUserIO(userAfter)
    }

    /**
     * ユーザIDを指定して1件削除
     */
    fun deleteUser(userId: Long): Boolean {
        return userMongodbRepository.deleteOne(userId) != null
    }

    /**
     * Mongo用のデータクラスをIO用にconvert(リスト用)
     */
    private fun userModelListToUserIO(list: List<USER>): List<UserIO.UserForListEntity> {
        return list.map { user ->
            UserIO.UserForListEntity(
                userId = user.userId,
                userName = user.userName
            )
        }.toList()
    }

    /**
     * Mongo用のデータクラスをIO用にconvert(詳細用)
     */
    private fun userModelToUserIO(user: USER): UserIO.UserForDetailEntity {
        return UserIO.UserForDetailEntity(
            userId = user.userId,
            userName = user.userName,
            createdAt = user.createdAt.format(DateTimeFormatter.ISO_DATE),
            updatedAt = user.updatedAt.format(DateTimeFormatter.ISO_DATE)
        )
    }

}