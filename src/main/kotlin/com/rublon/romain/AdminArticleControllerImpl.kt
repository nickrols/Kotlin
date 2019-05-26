package com.rublon.romain

class AdminArticleControllerImpl(val model: com.rublon.romain.AdminArticleModel) : com.rublon.romain.AdminArticleController {
    override fun postArticle(title: String, text: String) {
        if (text.isNotBlank() and text.isNotBlank())
            model.postArticle(title, text)
    }

    override fun deleteArticle(articleId: Int) {
        model.deleteArticle(articleId)
    }
}