package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rajnish.mydairy.R
import com.rajnish.mydairy.databinding.FragmentEditProfileBinding
import com.rajnish.mydairy.db.FamilyDetails
import com.rajnish.mydairy.db.Profile
import com.rajnish.mydairy.mvvm.model.ProfileModel


class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

private lateinit var binding:FragmentEditProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding = FragmentEditProfileBinding.bind(view)


        ProfileFragment.profileModel.user.observe(viewLifecycleOwner){

        binding.apply {
            nameEdit.setText(it?.name)
            emailEdit.setText(it?.email)
            phoneNumEdit.setText(it?.phoneNum)
            addressEdit.setText(it?.address)
            fatherNameEdit.setText(it?.familyDetails?.fatherName)
            motherNameEdit.setText(it?.familyDetails?.motherName)
            siblingsEdit.setText(it?.familyDetails?.siblings.toString())

            saveButton.setOnClickListener {
                val profile = Profile( nameEdit.text.toString(), phoneNumEdit.text.toString(), addressEdit.text.toString(), emailEdit.text.toString(),"", FamilyDetails(fatherNameEdit.text.toString(),motherNameEdit.text.toString(),
                    arrayListOf(siblingsEdit.text.toString())
                ) )
              ProfileFragment.profileModel.saveProfileOnFireBase(profile){status,message->
                  if (status){
                      Toast.makeText(requireContext(), "$message Added Successfully", Toast.LENGTH_SHORT).show()
                  }
                  else{

                      Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                  }
              }

            }



        }


        }





    }


}
