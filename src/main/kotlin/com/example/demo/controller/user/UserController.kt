package com.example.demo.controller.user

import com.example.demo.service.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("list")
    fun list(): UserIO.UserListResponse {
        return UserIO.UserListResponse(
            userList = userService.getUsers()
        )
    }

    @PostMapping("detail")
    fun detail(@RequestBody req: UserIO.UserDetailRequest): UserIO.UserDetailResponse {
        req.userId
            ?: throw Exception("[Invalid parameters] User id must not be null.")

        return UserIO.UserDetailResponse(
            user = userService.getUserByUserId(req.userId)
        )
    }

    @PostMapping("new")
    fun create(@RequestBody req: UserIO.UserCreateRequest): UserIO.UserCreateResponse {
        if (req.userName.isNullOrBlank())
            throw Exception("[Invalid parameters] Username must not be null.")

        return UserIO.UserCreateResponse(
            user = userService.createNewUser(req.userName)
        )
    }

    @PostMapping("update")
    fun update(@RequestBody req: UserIO.UserUpdateRequest): UserIO.UserUpdateResponse {
        req.userId
            ?: throw Exception("[Invalid parameters] User id must not be null.")

        if (req.userName.isNullOrBlank())
            throw Exception("[Invalid parameters] Username must not be null.")

        return UserIO.UserUpdateResponse(
            user = userService.updateUser(req.userId, req.userName)
        )
    }

    @PostMapping("delete")
    fun delete(@RequestBody req: UserIO.UserDeleteRequest): UserIO.UserDeleteResponse {
        req.userId
            ?: throw Exception("[Invalid parameters] User id must not be null.")

        return UserIO.UserDeleteResponse(
            result = userService.deleteUser(req.userId)
        )
    }
}