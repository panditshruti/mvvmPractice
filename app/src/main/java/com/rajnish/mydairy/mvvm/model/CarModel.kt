package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.api.ApiService
import com.rajnish.mydairy.db.Cardb
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarModel:ViewModel() {

    var carLiveData = MutableLiveData<Cardb?>()


fun getCarDetails(){
    val retrofit = Retrofit.Builder()
        .baseUrl("https://freetestapi.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)
    val call = apiService.getCar()

    call.enqueue(object :Callback<Cardb>{
        override fun onResponse(call: Call<Cardb>, response: Response<Cardb>) {

            if (response.isSuccessful){
                val cars = response.body()
                carLiveData .value = cars
                cars?.get(0)?.let { Log.d("MYApiCar", it.color) }
            }



        }

        override fun onFailure(call: Call<Cardb>, t: Throwable) {
            Log.e("MyApi", "Request failed", t)
        }


    })

}



}