package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.ApiService
import com.rajnish.mydairy.db.Cardb
import com.rajnish.mydairy.db.PostDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostDetailsModel:ViewModel() {

        var postDetailsLiveData = MutableLiveData<PostDetails?>()


        fun getPostDetails(tag:String){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/posts/tag/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.getPostDetails(tag)

            call.enqueue(object : Callback<PostDetails> {
                override fun onResponse(call: Call<PostDetails>, response: Response<PostDetails>) {

                    if (response.isSuccessful){
                        val postDetails = response.body()
                        postDetailsLiveData .value = postDetails
                      postDetails?.posts?.get(0)?.title.toString()

                        }
                    }

                override fun onFailure(call: Call<PostDetails>, t: Throwable) {
                    Log.e("MyApi", "Request failed", t)
                }


            })

            }

        }

