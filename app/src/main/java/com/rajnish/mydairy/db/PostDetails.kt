package com.rajnish.mydairy.db

data class PostDetails(
    val limit: Int,
    val posts: List<PostX>,
    val skip: Int,
    val total: Int
)