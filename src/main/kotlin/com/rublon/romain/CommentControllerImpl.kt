package com.rublon.romain

class CommentControllerImpl(val model: com.rublon.romain.CommentModel) : com.rublon.romain.CommentController {
    override fun deleteComment(commentId: Int) {
        model.deleteComment(commentId)
    }
}