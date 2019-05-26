package com.rublon.romain

import com.rublon.romain.SessionControllerImpl.sessionExists
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.*

object Route {
     fun Route.registerCommentRoutes(controller: com.rublon.romain.CommentController) {
        post("/article/{articleId}/comment/{id}") {
            val commentId = call.parameters["id"]!!.toInt()
            val articleId = call.parameters["articleId"]!!.toInt()
            controller.deleteComment(commentId)
            call.respondRedirect("/article/$articleId")
        }
    }

     fun Route.registerArticleRoutes(controller: com.rublon.romain.ArticleController) {
        route("/article/{id}") {
            get {
                val articleId = call.parameters["id"]!!.toInt()
                val article = controller.displayArticle(articleId)
                val session = call.sessions.get<com.rublon.romain.UserSession>()
                if (article != null)
                    call.respond(FreeMarkerContent("article.ftl", mapOf("article" to article, "session" to sessionExists(session)), "e"))
                else
                    call.respond(HttpStatusCode.NotFound)
            }
            post{
                val articleId = call.parameters["id"]!!.toInt()
                val post = call.receiveParameters()
                val com  = post["comment"]!!
                controller.postComment(articleId, com)
                call.respondRedirect("/article/$articleId")
            }
        }


        get("/") {
            val articles = controller.getListArticle()
            val session = call.sessions.get<com.rublon.romain.UserSession>()
            println(session)
            call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles, "session" to sessionExists(session)), "e"))
        }
    }

     fun Route.registerAdminArticleRoutes(controller: com.rublon.romain.AdminArticleController) {
        route("/article/post"){

            get {
                val session = call.sessions.get<com.rublon.romain.UserSession>()
                if (sessionExists(session) != null) {
                    call.respond(FreeMarkerContent("post.ftl",  mapOf("session" to sessionExists(session))))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }

            post {
                val session = call.sessions.get<com.rublon.romain.UserSession>()
                if (sessionExists(session) != null) {
                    val post = call.receiveParameters()
                    val articleTitle = post["articleTitle"]!!
                    val article  = post["article"]!!
                    controller.postArticle(articleTitle, article)
                    call.respondRedirect("/")
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
        post("/article/delete/{id}") {
            val session = call.sessions.get<com.rublon.romain.UserSession>()
            if (sessionExists(session) != null) {
                val articleId = call.parameters["id"]!!.toInt()
                controller.deleteArticle(articleId)
                call.respondRedirect("/")
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }

     fun Route.registerLoginRoutes(controller: com.rublon.romain.UserController) {
        route("/login") {
            get {
                val session = call.sessions.get<com.rublon.romain.UserSession>()
                if (sessionExists(session) != null) {
                    call.respondRedirect("/")
                } else {
                    call.respond(FreeMarkerContent("login.ftl", null))
                }
            }
            post {
                val post = call.receiveParameters()
                val session = call.sessions.get<com.rublon.romain.UserSession>()
                val username = post["username"]!!
                val password = post["password"]!!

                if (sessionExists(session) != null){
                    call.respond(HttpStatusCode.Forbidden)
                } else {
                    if (controller.logUser(username, password)) {
                        call.sessions.set(com.rublon.romain.UserSession(username))
                        call.respondRedirect("/")
                    } else {
                        call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "Invalid login")))
                    }
                }

            }
        }
        get("/logout"){
            call.sessions.clear<com.rublon.romain.UserSession>()
            call.respondRedirect("/")
        }
    }
}