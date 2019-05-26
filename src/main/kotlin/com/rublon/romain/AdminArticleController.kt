package com.rublon.romain

interface AdminArticleController {
    fun postArticle(title :String, text: String)

    fun deleteArticle(articleId: Int)
}