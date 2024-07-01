package com.rajnish.mydairy.uiFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.rajnish.mydairy.R
import com.rajnish.mydairy.databinding.FragmentUserProfileBinding
import com.rajnish.mydairy.mvvm.model.UserProfileModel

class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {

        private lateinit var binding: FragmentUserProfileBinding
        private lateinit var userProfileModel:UserProfileModel

        @SuppressLint("SetTextI18n")
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding = FragmentUserProfileBinding.bind(view)

             userProfileModel = ViewModelProvider(this)[UserProfileModel::class.java]

            userProfileModel.getUserProfile(arguments?.getString("id")!!)
            userProfileModel.userProfileLiveData.observe(viewLifecycleOwner){userProfile ->

                Glide.with(this).load(userProfile?.image).into(binding.userImage)
                binding.apply {

                    if (userProfile !=null){


                userFullName.text = "${userProfile.firstName} ${userProfile.lastName}"
                userEmail.text = "Email: ${userProfile.email}"
                userPhone.text = "Phone: ${userProfile.phone}"
                userAddress.text = "Address: ${userProfile.address.address}, ${userProfile.address.city}, ${userProfile.address.state}, ${userProfile.address.stateCode}"
                userAge.text = "Age: ${userProfile.age}"
                userGender.text = "Gender: ${userProfile.gender}"
                userBirthDate.text = "Birth Date: ${userProfile.birthDate}"
                userBloodGroup.text = "Blood Group: ${userProfile.bloodGroup}"
                userHeight.text = "Height: ${userProfile.height} cm"
                userWeight.text = "Weight: ${userProfile.weight} kg"
                userCompany.text = "Company: ${userProfile.company.name}"
                userRole.text = "Role: ${userProfile.role}"
                userBank.text = "Bank: ${userProfile.bank.cardNumber}, ${userProfile.bank.cardExpire}"
                    }
                }
            }

            }

}
