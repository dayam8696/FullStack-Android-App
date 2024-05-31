package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fullstackapp.R
import com.example.fullstackapp.adapter.RvContactsAdapter
import com.example.fullstackapp.databinding.HomeFragmentBinding
import com.example.fullstackapp.models.Contacts
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : BaseFragment() {
    private val binding by lazy { HomeFragmentBinding.inflate(layoutInflater) }
    private lateinit var contactList: ArrayList<Contacts>
    private lateinit var firebaseRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
        contactList = arrayListOf()
        fetchData()
        binding.rvContacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }

        return binding.root
    }

    private fun fetchData() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                contactList.clear()
                if (snapshot.exists()) {
                    for (contactSnap in snapshot.children) {
                        val contact = contactSnap.getValue(Contacts::class.java)
                        contactList.add(contact!!)
                    }
                }
                val rvAdapter = RvContactsAdapter(contactList)
                binding.rvContacts.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                showToast("error $error")
            }

        })
    }
}

