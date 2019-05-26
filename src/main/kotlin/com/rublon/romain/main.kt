package com.rublon.romain

import com.rublon.romain.Route.registerAdminArticleRoutes
import com.rublon.romain.Route.registerArticleRoutes
import com.rublon.romain.Route.registerCommentRoutes
import com.rublon.romain.Route.registerLoginRoutes
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import freemarker.cache.*

import io.ktor.freemarker.*
import io.ktor.http.content.resources
import io.ktor.http.content.static

import io.ktor.sessions.*



fun main(args: Array<String>) {

    val pool = com.rublon.romain.ConnectionPool("jdbc:mysql://localhost/CMS?serverTimezone=UTC", "root", "")
//    val pool = ConnectionPool("jdbc:mysql://localhost/kotlin", "root", "root")

    val articleModel: com.rublon.romain.ArticleModel = com.rublon.romain.ArticleMysqlModel(pool)
    val articleController: com.rublon.romain.ArticleController = com.rublon.romain.ArticleControllerImpl(articleModel)

    val adminArticleModel: com.rublon.romain.AdminArticleModel = com.rublon.romain.AdminArticleMysqlModel(pool)
    val adminArticleController: com.rublon.romain.AdminArticleController = com.rublon.romain.AdminArticleControllerImpl(adminArticleModel)

    val commentModel: com.rublon.romain.CommentModel = com.rublon.romain.CommentMysqlModel(pool)
    val commentController: com.rublon.romain.CommentController = com.rublon.romain.CommentControllerImpl(commentModel)

    val userModel: com.rublon.romain.UserModel = com.rublon.romain.UserMysqlModel(pool)
    val userController: com.rublon.romain.UserController = com.rublon.romain.UserControllerImpl(userModel)

    val server = embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8000) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }

        install(Sessions) {
            cookie<com.rublon.romain.UserSession>("SESSION"){
                cookie.path = "/"
            }

        }

        routing {
            registerArticleRoutes(articleController)
            registerAdminArticleRoutes(adminArticleController)
            registerCommentRoutes(commentController)
            registerLoginRoutes(userController)

            //Static ressources (like css files)
            static("/static") {
                resources("static")
            }
        }

    }
    server.start(wait = true)
}
