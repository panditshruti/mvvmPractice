package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rajnish.mydairy.R
import com.rajnish.mydairy.databinding.FragmentProfileBinding
import com.rajnish.mydairy.db.FamilyDetails
import com.rajnish.mydairy.db.Profile
import com.rajnish.mydairy.mvvm.model.ProfileModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding


    companion object{
        lateinit var profileModel: ProfileModel
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)


        profileModel = ViewModelProvider(this)[ProfileModel::class.java]
        profileModel.getProfileFromFireBase("7739717389")

        profileModel.user.observe(viewLifecycleOwner) {
            binding.apply {

                name.text = it?.name
                phoneNum.text = it?.phoneNum
                address.text = it?.address
                email.text = it?.email
                fatherName.text = it?.familyDetails?.fatherName
                motherName.text = it?.familyDetails?.motherName
                siblings.text = it?.familyDetails?.siblings.toString()

                editBtn.setOnClickListener {

                    val action =
                        ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
                    findNavController().navigate(action)

                }

            }

        }

        profileModel.getprofileInCallBack(8){

        }

        profileModel.deleteData("7739717389")

    }
}
