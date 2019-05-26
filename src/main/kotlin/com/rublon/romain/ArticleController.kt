package com.rublon.romain

import io.ktor.freemarker.FreeMarkerContent

interface ArticleController {
    fun displayArticle(id: Int): com.rublon.romain.Article?

    fun getListArticle(): List<com.rublon.romain.Article>

    fun postComment(articleId: Int, text: String)
}