package com.example.fullstackapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.fullstackapp.R
import com.example.fullstackapp.databinding.RvContactsItemBinding
import com.example.fullstackapp.fragments.HomeFragmentDirections
import com.example.fullstackapp.models.Contacts

class RvContactsAdapter(private val contactList: ArrayList<Contacts>) :
    RecyclerView.Adapter<RvContactsAdapter.ViewHolder>() {
    class ViewHolder(val binding: RvContactsItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvContactsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = contactList[position]
        holder.apply {
            binding.apply {
                tvNameItem.text = currentItem.name
                tvPhoneItem.text = currentItem.phoneNumber
                tvIdItem.text = currentItem.id
                rvContainer.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToUpdateFragment(
                        currentItem.id.toString(),
                        currentItem.name.toString(),
                        currentItem.phoneNumber.toString()
                    )
                    findNavController(holder.itemView).navigate(action)
                }
            }
        }
    }
}