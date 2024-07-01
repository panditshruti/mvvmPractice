package com.rajnish.mydairy.mvvm.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rajnish.mydairy.Employees
import com.rajnish.mydairy.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EmployeesModel:ViewModel() {
    var employeesLiveData = MutableLiveData<Employees?>()
    var x = MutableLiveData<Employees>()


    fun getEmployees(){
        val rerofit = Retrofit.Builder()
            .baseUrl("https://dummy.restapiexample.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = rerofit.create(ApiService::class.java)
        val call = apiService.getEmployeesData()

        call.enqueue(object : Callback<Employees> {
            override fun onResponse(call: Call<Employees>, response: Response<Employees>) {
                if (response.isSuccessful) {
                    val employees = response.body()
                    employeesLiveData.value= employees
                    Log.d("MYApi", employees!!.data[0].employee_name)
                }
            }

            override fun onFailure(call: Call<Employees>, t: Throwable) {
                Log.e("MyApi", "Request failed", t)
            }


        })


    }

}