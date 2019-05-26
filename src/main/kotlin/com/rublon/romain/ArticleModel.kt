package com.rublon.romain

interface ArticleModel {
    fun getArticleText(articleId: Int): String?

    fun getArticleComments(articleId: Int): List<com.rublon.romain.Comment>

    fun getListArticle(): List<com.rublon.romain.Article>

    fun getArticleTitle(articleId: Int): String?

    fun postComment(articleId: Int, text: String)
}