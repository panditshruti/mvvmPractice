package com.rajnish.mydairy.uiFragment


import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rajnish.mydairy.R
import com.rajnish.mydairy.adapter.TaskAdapter
import com.rajnish.mydairy.adapter.interfaceForListner.ItemDismissListener
import com.rajnish.mydairy.databinding.FragmentEditMyDairyBinding
import com.rajnish.mydairy.invoice.MarriageBioDataClass
import com.rajnish.mydairy.mvvm.model.TaskModel
import com.rajnish.mydairy.mvvm.model.TaskModelFactory
import com.rajnish.mydairy.mvvm.repository.TaskRepository
import com.rajnish.mydairy.room.Task
import com.rajnish.mydairy.room.TaskDatabase
import com.rajnish.mydairy.util.AddTaskDialogFragment
import javax.inject.Inject

class EditMyDairyFragment : Fragment(R.layout.fragment_edit_my_dairy) {

    private var _binding: FragmentEditMyDairyBinding? = null
    private lateinit var taskAdapter: TaskAdapter
    private val binding get() = _binding!!
    @Inject
    lateinit var taskModel: TaskModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEditMyDairyBinding.bind(view)

        val tskDatabase = TaskDatabase.getDatabase(requireContext())
        val tskDao = tskDatabase.taskDao()
        val taskRepository = TaskRepository(tskDao)

        taskModel = ViewModelProvider(this, TaskModelFactory(taskRepository))[TaskModel::class.java]






        binding.addTask.setOnClickListener {
            val addTaskDialog = AddTaskDialogFragment()
            addTaskDialog.setListener(object : AddTaskDialogFragment.AddTaskDialogListener {
                override fun onTaskAdded(task: Task) {
                    taskModel.insertTask(task)
                    Toast.makeText(requireContext(), "Task added successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            })
            addTaskDialog.show(parentFragmentManager, "AddTaskDialog")
        }

        taskModel.getAllTask().observe(viewLifecycleOwner) {
            if (it.isEmpty()){
                binding.tryAgainAnimation.visibility = View.VISIBLE

            }else {
                binding.tryAgainAnimation.visibility = View.GONE

                taskAdapter = TaskAdapter(it, object : ItemDismissListener {
                    override fun onItemDismiss(itemId: Long) {
                        taskModel.deleteTask(itemId)
                    }
                })
                binding.taskRecycleView.adapter = taskAdapter
                binding.taskRecycleView.layoutManager = LinearLayoutManager(requireContext())
            }

        }
        taskModel.getTitle("2024-04-17 16:45:00").observe(viewLifecycleOwner){

        }

        taskModel.getTitleBetweenGivenDate("2024-04-25 16:45:00","2024-04-29 16:45:00").observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_SHORT).show()
        }


        val marriageArrayList = arrayListOf(
            "Name",
            "Father Name",
            "Mother Name",
            "Date Of Birth",
            "Qualification",
            "Village",
            "Post",
            "District",
            "State",
            "Village",
            "Post",
            "District",
            "State"
        )
        for (item in marriageArrayList) {
            val next = EditText(requireContext())
            next.hint = item
            next.textSize = 16F

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 0, 0, 16) // Set bottom margin to 16dp converted to pixels
            // layoutParams.bottomMargin = 10.dpToPx() // No need for this line

            next.layoutParams = layoutParams
            next.width = 70 // Set width in pixels

            binding.linearlayoutForDymanic.addView(next)

            val marriage = MarriageBioDataClass()
            marriage.templet1(requireContext())
        }

        // Extension function to convert dp to pixels
        fun Float.dpToPx(): Int {
            return (this * Resources.getSystem().displayMetrics.density).toInt()
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
