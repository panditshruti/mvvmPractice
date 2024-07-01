package com.rajnish.mydairy.api

import com.rajnish.mydairy.Employees
import com.rajnish.mydairy.db.Cardb
import com.rajnish.mydairy.db.Gk
import com.rajnish.mydairy.db.PostDetails
import com.rajnish.mydairy.db.PostTag
import com.rajnish.mydairy.db.UserDb
import com.rajnish.mydairy.db.UserProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("employees")
    fun getEmployeesData():Call<Employees>

    @GET("api.php?amount=10")
    fun getGk():Call<Gk>


    @GET("cars")
    fun getCar():Call<Cardb>

    @Multipart
    @POST("/users/register")
    fun registerUser(@Body userDb: UserDb):Call<UserDb>

    @GET("tags")
    fun getPostTag():Call<PostTag>
 @GET("{tag}")
    fun getPostDetails(@Path("tag") tag:String):Call<PostDetails>

    @GET("{id}")
    fun getUserProfile(@Path("id") id:String):Call<UserProfile>

}

