package com.rublon.romain

import org.mindrot.jbcrypt.BCrypt

class UserMysqlModel(val pool: com.rublon.romain.ConnectionPool) : com.rublon.romain.UserModel {

    override fun logUser(username: String, password: String) : Boolean {
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM user WHERE username = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.execute()
                val rs = stmt.resultSet
                if (rs.next()) {
                    val hash = rs.getString("password")
                    print(hash);
                    //Vérification du mot de passe avec Bcrypt
                    /*val yespass=BCrypt.hashpw(password, BCrypt.gensalt(12));
                    print("     ");
                    print(yespass);
                    print("     ");*/
                    if (BCrypt.checkpw(password, hash)) {
                        return true
                    }
                }
            }
        }
        return false
    }
}