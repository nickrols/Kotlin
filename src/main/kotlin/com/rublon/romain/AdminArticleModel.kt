package com.rublon.romain

interface AdminArticleModel {
    fun postArticle(title: String, text: String)

    fun deleteArticle(articleId: Int)
}