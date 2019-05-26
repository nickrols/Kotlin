package com.rublon.romain

object SessionControllerImpl: com.rublon.romain.SessionController {
    override fun sessionExists(session: com.rublon.romain.UserSession?): String? {
        if (session != null)
            return session.username
        return null
    }
}