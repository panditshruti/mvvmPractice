package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.RetrofitInstance
import com.rajnish.mydairy.db.Gk
import com.rajnish.mydairy.db.UserDb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserAuthModel:ViewModel() {


    fun registerUser(userDb: UserDb){

        val call = RetrofitInstance.api.registerUser(userDb)

        call.enqueue(object : Callback<UserDb> {
            override fun onResponse(call: Call<UserDb>, response: Response<UserDb>) {
                if (response.isSuccessful) {
                    val gk = response.body()

                    Log.d("MyApi",gk!!.fullname)

                }
            }

            override fun onFailure(call: Call<UserDb>, t: Throwable) {
                t.printStackTrace()
                println("API call failed: ${t.message}")
            }
        })


    }


}