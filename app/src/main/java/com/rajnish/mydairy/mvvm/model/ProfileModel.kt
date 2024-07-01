package com.rajnish.mydairy.mvvm.model


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.rajnish.mydairy.db.FamilyDetails
import com.rajnish.mydairy.db.Profile

class ProfileModel : ViewModel() {

    private var profileDatabase: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Profile")
    var user = MutableLiveData<Profile?>()

    fun saveProfileOnFireBase(profile: Profile,callBack:(Boolean,String)->Unit) {
        profileDatabase.child(profile.phoneNum).setValue(profile).addOnCompleteListener {
           callBack(true,profile.phoneNum)
        }.addOnFailureListener {
            callBack(false,it.message.toString())

        }
    }
    fun getProfileFromFireBase(number: String) {
        profileDatabase.child(number).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val profile = snapshot.getValue(Profile::class.java)
                    user.value = profile

                }
                else {
                    user.value = null // Profile does not exist
                }
            }
            override fun onCancelled(error: DatabaseError) {
                user.value = null // Handle error case
                // Log error if necessary
            }
        })
    }

    fun getprofileInCallBack(profile: Int,callBack:(profile:Int)->Unit){

        callBack(9)

    }

    fun deleteData(number: String){
        profileDatabase.child("hi").child("hello").child("skljdfhlk").setValue(null)



    }

}
