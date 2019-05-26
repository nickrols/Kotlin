package com.rublon.romain


class ArticleControllerImpl(val model: com.rublon.romain.ArticleModel) : com.rublon.romain.ArticleController {
    override fun getListArticle(): List<com.rublon.romain.Article> {
        return model.getListArticle()
    }

    override fun displayArticle(id: Int): com.rublon.romain.Article?{
        val title = model.getArticleTitle(id)?: return null
        val text = model.getArticleText(id) ?: return null
        val comments = model.getArticleComments(id)
        return com.rublon.romain.Article(id, text, title, comments)
    }
    override fun postComment(articleId: Int, text: String){
        if (text.isNotBlank())
            model.postComment(articleId, text)
    }

}