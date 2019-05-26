package com.rublon.romain

interface SessionController {
    fun sessionExists(session: com.rublon.romain.UserSession?): String?
}