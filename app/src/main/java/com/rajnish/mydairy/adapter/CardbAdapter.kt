package com.rajnish.mydairy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajnish.mydairy.R
import com.rajnish.mydairy.db.Cardb
import com.rajnish.mydairy.db.CardbItem

class CardbAdapter(private val items: Cardb) : RecyclerView.Adapter<CardbAdapter.CardbViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardbViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardb, parent, false)
        return CardbViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardbViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CardbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textViewMake: TextView = itemView.findViewById(R.id.textViewMake)
        private val textViewModel: TextView = itemView.findViewById(R.id.textViewModel)
        // Define other views

        fun bind(item: CardbItem) {
            textViewMake.text = item.make
            textViewModel.text = item.model
            // Bind other fields

            Glide.with(itemView.context).load(item.image).into(imageView)
        }
    }
}