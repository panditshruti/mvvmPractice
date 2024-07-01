package com.rajnish.mydairy.db

import java.io.File

data class UserDb(
    val fullname:String,
    val email:String,
    val password:String,
    val username:String,
    val avatar:File,
    val coverImage:File

)
