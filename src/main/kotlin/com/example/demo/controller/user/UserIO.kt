package com.example.demo.controller.user

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * ユーザ関連APIの RequestBody および ResponseBody のデータ構造を定義するためのクラス
 */
class UserIO {

    /**
     * 一覧取得API用
     */
    data class UserListResponse(
        @field:JsonProperty("ulist") val userList: List<UserForListEntity>
    )

    /**
     * 詳細取得API用
     */
    data class UserDetailRequest(
        @field:JsonProperty("uid") val userId: Long? = 0L,
    )

    data class UserDetailResponse(
        @field:JsonProperty("udata") val user: UserForDetailEntity,
    )

    /**
     * 登録API用
     */
    data class UserCreateRequest(
        @field:JsonProperty("uname") val userName: String?,
    )

    data class UserCreateResponse(
        @field:JsonProperty("udata") val user: UserForDetailEntity,
    )

    /**
     * 更新API用
     */
    data class UserUpdateRequest(
        @field:JsonProperty("uid") val userId: Long? = 0L,
        @field:JsonProperty("uname") val userName: String?,
    )

    data class UserUpdateResponse(
        @field:JsonProperty("udata") val user: UserForDetailEntity,
    )

    /**
     * 削除API用
     */
    data class UserDeleteRequest(
        @field: JsonProperty("uid") val userId: Long? = 0L,
    )

    data class UserDeleteResponse(
        @field:JsonProperty("rst") val result: Boolean,
    )

    /**
     * 一覧用の簡易的なユーザエンティティ
     */
    data class UserForListEntity(
        @field:JsonProperty("uid") val userId: Long = 0L,
        @field:JsonProperty("uname") val userName: String = "NoName",
    )

    /**
     * IO共通ユーザエンティティ
     */
    data class UserForDetailEntity(
        @field:JsonProperty("uid") val userId: Long = 0L,
        @field:JsonProperty("uname") val userName: String = "NoName",
        @field:JsonProperty("ct") val createdAt: String = "",
        @field:JsonProperty("ut") val updatedAt: String = "",
    )

}
