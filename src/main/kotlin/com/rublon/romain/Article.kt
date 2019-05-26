package com.rublon.romain

@Suppress("unused")
data class Article(val id: Int, var text: String, var title: String, var comments: List<com.rublon.romain.Comment>)