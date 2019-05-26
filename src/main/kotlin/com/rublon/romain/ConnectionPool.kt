package com.rublon.romain

import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.ConcurrentLinkedQueue

class ConnectionPool(private val url: String?, private val user: String?, private val pass: String?) {

    private val poolConnection = ConcurrentLinkedQueue<Connection>()

    fun getConnection(): Connection {
        // Either create a new connection or reuse an existing connection
        return poolConnection.poll() ?: DriverManager.getConnection( url, user, pass)
    }

    fun releaseConnection(c: Connection){
        // Save the connection for later use
        poolConnection.add(c)
    }

    inline fun useConnection(f: (Connection) -> Unit) {
        val c  = getConnection()
        try {
            f(c)
        } finally {
            releaseConnection(c)
        }
    }
}