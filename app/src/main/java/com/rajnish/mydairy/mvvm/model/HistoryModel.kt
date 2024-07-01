package com.rajnish.mydairy.mvvm.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistoryModel : ViewModel() {
     var x = MutableLiveData(0)


    fun increaseValue(){
        x.value = x.value?.plus(1)
    }
    fun decreaseValue(){
        x.value = x.value?.minus(1)
    }

    fun customValue(customNum:Int){
        x.value = customNum + x.value!!
    }


}