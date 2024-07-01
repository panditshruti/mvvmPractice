package com.rajnish.mydairy.mvvm.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import kotlin.random.Random

class ProductModel : ViewModel() {

var x = MutableLiveData(0)

    fun increaseValue(){
        x.value = x.value?.plus(1)
    }

    fun decreaseValue(){
        x.value = x.value?.minus(2)
    }
    fun customNum(edNUm:Int){
        x.value = edNUm + x.value!!

    }

    fun setValueFirebase(){
        val firebase = FirebaseDatabase.getInstance().reference.child("MyDairy")
        firebase.setValue(x.value)
    }
    fun someOfTwoNum(x:Int,y:Int):LiveData<Int>{
        var result = MutableLiveData(x + y)
        return  result
    }

    fun randomNum(): LiveData<Int> {
        return MutableLiveData(Random.nextInt(6))

    }

}