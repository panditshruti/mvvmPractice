package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.ApiService
import com.rajnish.mydairy.db.PostTag
import com.rajnish.mydairy.db.UserProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserProfileModel:ViewModel() {

        var userProfileLiveData = MutableLiveData<UserProfile?>()

        fun getUserProfile(id:String) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val apiService = retrofit.create(ApiService::class.java)
            val call = apiService.getUserProfile(id)

            call.enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.isSuccessful) {
                        val postTag = response.body()
                        userProfileLiveData.value = postTag
                        Log.d("MYApi", postTag.toString())
                    }
                }
                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Log.e("MYApi", "Request failed", t)
                }
            })
        }
    }
