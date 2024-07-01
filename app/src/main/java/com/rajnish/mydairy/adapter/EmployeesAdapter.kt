package com.rajnish.mydairy.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rajnish.mydairy.Employees
import com.rajnish.mydairy.R

class EmployeesAdapter(private val employees: Employees) : RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val name: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_employee, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
//        val employee = employees[position]
//        holder.nameTextView.text = employee.name
//        holder.name.text = employee.position
    }

    override fun getItemCount(): Int {
        return 0
    }
}
