package com.rublon.romain

interface UserController {
    fun logUser(username: String, password: String) : Boolean
}