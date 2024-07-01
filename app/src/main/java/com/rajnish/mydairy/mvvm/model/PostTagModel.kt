package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.ApiService
import com.rajnish.mydairy.db.PostTag
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostTagModel : ViewModel() {

    var postTagLiveData = MutableLiveData<PostTag?>()

    fun getPostTag() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/posts/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getPostTag()

        call.enqueue(object : Callback<PostTag> {
            override fun onResponse(call: Call<PostTag>, response: Response<PostTag>) {
                if (response.isSuccessful) {
                    val postTag = response.body()
                    postTagLiveData.value = postTag
                    Log.d("MYApi", postTag.toString())
                }
            }

            override fun onFailure(call: Call<PostTag>, t: Throwable) {
                Log.e("MYApi", "Request failed", t)
            }
        })
    }
}
