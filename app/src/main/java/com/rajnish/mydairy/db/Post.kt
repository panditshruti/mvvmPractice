package com.rajnish.mydairy.db

data class Post(
    val body: String,
    val id: Int,
    val reactions: Reactions,
    val tags: List<String>,
    val title: String,
    val userId: Int,
    val views: Int
)