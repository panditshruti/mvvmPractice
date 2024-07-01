package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.RetrofitInstance
import com.rajnish.mydairy.db.Gk
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GkModel :ViewModel() {

    fun getGk(){
        val call = RetrofitInstance.api.getGk()

        call.enqueue(object : Callback<Gk> {
            override fun onResponse(call: Call<Gk>, response: Response<Gk>) {
                if (response.isSuccessful) {
                    val gk = response.body()

                    Log.d("MyApi",gk!!.results[0].question)

                }
            }

            override fun onFailure(call: Call<Gk>, t: Throwable) {
                t.printStackTrace()
                println("API call failed: ${t.message}")
            }
        })



    }


}