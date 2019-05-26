package com.rublon.romain

class CommentMysqlModel(val pool: com.rublon.romain.ConnectionPool) : com.rublon.romain.CommentModel {
    override fun deleteComment(commentId: Int) {
        print("ta meeere");
        pool.useConnection{ conn ->
            conn.prepareStatement("DELETE FROM comment WHERE comment.id = ?").use { stmt ->
                stmt.setInt(1, commentId)
                stmt.execute()
            }
        }
    }
}