package com.rajnish.mydairy.uiFragment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rajnish.mydairy.R
import com.rajnish.mydairy.databinding.FragmentEditMyDairyBinding
import com.rajnish.mydairy.firebase.TaskFirebase
import com.rajnish.mydairy.mvvm.model.TaskModel
import com.rajnish.mydairy.mvvm.model.TaskModelFactory
import com.rajnish.mydairy.mvvm.repository.TaskRepository
import com.rajnish.mydairy.room.Task
import com.rajnish.mydairy.room.TaskDatabase
import javax.inject.Inject

class EditMyDairyFragment : Fragment(R.layout.fragment_edit_my_dairy) {

    private  var _binding:FragmentEditMyDairyBinding ?= null
    private val binding get() = _binding!!
    @Inject lateinit var taskModel: TaskModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditMyDairyBinding.bind(view)

        val tskDatabase = TaskDatabase.getDatabase(requireContext())
        val tskDao = tskDatabase.taskDao()
        val taskRepository = TaskRepository(tskDao)

        taskModel = ViewModelProvider(this,TaskModelFactory(taskRepository))[TaskModel::class.java]


        taskRepository.tasksLiveData.observe(viewLifecycleOwner){

            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            for (data in it){
                val details = Task(0,data.title,data.desc,"8787")
                taskModel.insertTask(details)
            }
        }

        taskModel.getAllTask().observe(viewLifecycleOwner){
            if (it.isEmpty()){
                taskRepository.retrieveTasksFromFirebase()
            }else {
                val adapter =
                    ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
                binding.listView.adapter = adapter
            }
        }

        binding.submitbtn.setOnClickListener {
            taskModel.addTaskToFirebase(TaskFirebase("Rajish","kuch nahi"))
            taskRepository.retrieveTasksFromFirebase()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
