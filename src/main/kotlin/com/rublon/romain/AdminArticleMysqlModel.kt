package com.rublon.romain

class AdminArticleMysqlModel(val pool: com.rublon.romain.ConnectionPool): com.rublon.romain.AdminArticleModel {
    override fun postArticle(title: String, text: String){
        pool.useConnection{ conn ->
            conn.prepareStatement("INSERT INTO article (title, text) VALUES (?, ?)").use { stmt ->
                stmt.setString(1, title)
                stmt.setString(2, text)
                stmt.execute()
            }
        }
    }

    override fun deleteArticle(articleId: Int) {
        pool.useConnection{ conn ->
            //Suppression des commentaires
            conn.prepareStatement("DELETE FROM comment WHERE id_article = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
            }
            //Suppression de l'article
            conn.prepareStatement("DELETE FROM article WHERE article.id = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
            }
        }
    }
}