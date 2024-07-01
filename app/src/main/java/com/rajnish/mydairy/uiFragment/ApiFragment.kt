package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.CardbAdapter
import com.rajnish.mydairy.databinding.FragmentApiBinding
import com.rajnish.mydairy.db.Cardb
import com.rajnish.mydairy.mvvm.model.CarModel
import com.rajnish.mydairy.mvvm.model.EmployeesModel


class ApiFragment : Fragment(R.layout.fragment_api) {
private lateinit var binding: FragmentApiBinding
private lateinit var employeesModel: EmployeesModel
private lateinit var carModel: CarModel
private lateinit var carAdapter: CardbAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentApiBinding.bind(view)



//        employeesModel = ViewModelProvider(this)[EmployeesModel::class.java]
        carModel = ViewModelProvider(this)[CarModel::class.java]
//
//        employeesModel.getEmployees()
        carModel.getCarDetails()
        carModel.carLiveData.observe(viewLifecycleOwner){
            carAdapter = CardbAdapter(it!!)
            binding.recyclerView.adapter = carAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

//        employeesModel.employeesLiveData.observe(viewLifecycleOwner){
//
//            if (it != null) {
//                Toast.makeText(requireContext(), it.data[0].employee_name, Toast.LENGTH_SHORT).show()
//            }
//        }

        }


}
