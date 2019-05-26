package com.rublon.romain

import io.ktor.http.HttpStatusCode

class ArticleMysqlModel(val pool: com.rublon.romain.ConnectionPool) : com.rublon.romain.ArticleModel {
    override fun getListArticle(): List<com.rublon.romain.Article> {
        var articles: List<com.rublon.romain.Article> = emptyList()
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM article").use { stmt ->
                stmt.executeQuery().use { results ->
                    while (results.next()) {
                        articles += com.rublon.romain.Article(results.getInt(1), results.getString(2), results.getString(3),emptyList())
                    }
                }
            }
        }
        return articles
    }

    override fun getArticleText(articleId: Int): String?{
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM article WHERE id = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
                val rs = stmt.resultSet
                if (rs.next())
                    return rs.getString("text")
            }

        }
        return null
    }

    override fun getArticleTitle(articleId: Int): String? {
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT title FROM article WHERE id = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.execute()
                val rs = stmt.resultSet
                if (rs.next())
                    return rs.getString("title")
            }

        }
        return null
    }

    override fun getArticleComments(articleId: Int): List<com.rublon.romain.Comment>{
        var commentaires: List<com.rublon.romain.Comment> = emptyList()
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM comment WHERE id_article = ?").use { stmt ->
                stmt.setInt(1, articleId)
                stmt.executeQuery().use { results ->
                    while (results.next()) {
                        commentaires += com.rublon.romain.Comment(results.getInt(1), results.getString(3))
                    }
                }
            }
        }
        return commentaires
    }
    override fun postComment(articleId: Int, text: String){
        pool.useConnection{ conn ->
            conn.prepareStatement("INSERT INTO comment (text, id_article) VALUES (?, ?)").use { stmt ->
                stmt.setString(1, text)
                stmt.setInt(2, articleId)
                stmt.execute()
            }
        }
    }
}