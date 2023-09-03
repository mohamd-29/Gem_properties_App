package com.app.gemproperties.fragments.listings

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.gemproperties.R
import com.app.gemproperties.databinding.ItemBinding

class ItemAdapter : RecyclerView.Adapter<ItemAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<House>() {
        override fun areItemsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: House, newItem: House): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var houses: List<House>
        get() = differ.currentList
        set(value) { differ.submitList(value) }

    override fun getItemCount() = houses.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val house = houses[position]

        holder.binding.apply {
            val house = houses[position]
            name.text = house.title
            complete.isChecked = house.complete
        }
        holder.itemView.findViewById<ImageView>(R.id.imageView6).setOnClickListener {


            val action=ListingFragmentDirections.actionListingFragmentToUpdateFragment(house)
            holder.itemView.findNavController().navigate(action)

        }

//        val builder = AlertDialog.Builder(holder.itemView.context)
//        builder.setPositiveButton("Confirm") { _, _ ->
//            RetrofitInstance.api.deleteTask(house.id)
//            notifyItemRemoved(position)
//
//            Toast.makeText(
//                holder.itemView.context,
//                " successfully deleted ",
//                Toast.LENGTH_SHORT
//            ).show()
//
//        }
//        builder.setNegativeButton("Cancel") { _, _ -> }
//        builder.setTitle(" Delete Info ?")
//        builder.setMessage("Are you sure you want to delete  ? ")
//        builder.create().show()
    }
}