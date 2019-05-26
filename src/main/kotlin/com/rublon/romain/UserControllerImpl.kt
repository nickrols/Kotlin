package com.rublon.romain

class UserControllerImpl(val model: com.rublon.romain.UserModel) : com.rublon.romain.UserController {

    override fun logUser(username: String, password: String) : Boolean {
        if (username.isNotBlank() && password.isNotBlank())
            return model.logUser(username, password)
        return false
    }
}