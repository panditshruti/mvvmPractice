package com.rajnish.mydairy.db

data class PostX(
    val body: String,
    val id: Int,
    val reactions: ReactionsX,
    val tags: List<String>,
    val title: String,
    val userId: Int,
    val views: Int
)