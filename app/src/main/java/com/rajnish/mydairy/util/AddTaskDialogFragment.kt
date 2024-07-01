package com.rajnish.mydairy.util

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.rajnish.mydairy.R
import com.rajnish.mydairy.room.Task
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AddTaskDialogFragment : DialogFragment() {

    interface AddTaskDialogListener {
        fun onTaskAdded(task: Task)
    }

    private var listener: AddTaskDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_add_task, null)
            val titleEditText = dialogView.findViewById<EditText>(R.id.editTextTitle)
            val descriptionEditText = dialogView.findViewById<EditText>(R.id.editTextDescription)

            val builder = AlertDialog.Builder(it)
            builder.setView(dialogView)
                .setTitle("Add Task")
                .setPositiveButton(R.string.add) { dialog, _ ->
                    val title = titleEditText.text.toString().trim()
                    val description = descriptionEditText.text.toString().trim()
                    val currentDateTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    val formattedDateTime = currentDateTime.format(formatter)
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        val task = Task(0, title, description, formattedDateTime)
                        listener?.onTaskAdded(task)
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    fun setListener(listener: AddTaskDialogListener) {
        this.listener = listener
    }
}
