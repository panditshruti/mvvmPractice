package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rajnish.mydairy.R
import com.rajnish.mydairy.databinding.FragmentProductBinding
import com.rajnish.mydairy.db.FamilyDetails
import com.rajnish.mydairy.db.Profile
import com.rajnish.mydairy.mvvm.model.ProfileModel

class ProductFragment : Fragment(R.layout.fragment_product) {

    private lateinit var binding: FragmentProductBinding
    private lateinit var profileModel: ProfileModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        profileModel = ViewModelProvider(this)[ProfileModel::class.java]


        profileModel.saveProfileOnFireBase(
            Profile(
                "raj",
                "7739717389",
                "singarahiya,sitamarhi,Bihar,India",
                "shrutikumaripandit@gmail.com",
                "bihar409560945",
                FamilyDetails("Vinay Kumar Pandit", "Gita Kumari", arrayListOf("shristi", "Raj"))
            )
        ) {status, message ->
            if (status) {
                Toast.makeText(requireContext(), "$message Added Sucsefully", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
            }




        }
    }

}
