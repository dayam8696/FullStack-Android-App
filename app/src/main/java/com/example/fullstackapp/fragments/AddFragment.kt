package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.fullstackapp.R
import com.example.fullstackapp.databinding.AddFragmentBinding
import com.example.fullstackapp.models.Contacts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddFragment : BaseFragment() {
    private val binding by lazy { AddFragmentBinding.inflate(layoutInflater) }
    private lateinit var firebaseRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")
        binding.btnSend.setOnClickListener {
            saveData()
        }
        binding.navtp.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_saveFragment)
        }
        return binding.root




    }

    private fun saveData() {
        val name = binding.edtName.text.toString()
        val phone = binding.edtPhone.text.toString()

        if (name.isEmpty()) binding.edtName.error = "Write a name"
        if (phone.isEmpty()) binding.edtName.error = "Write a name"
        val contactId = firebaseRef.push().key!!
        val contacts = Contacts(contactId, name, phone)

        firebaseRef.child(contactId).setValue(contacts)
            .addOnCompleteListener {
                Toast.makeText(context,"Data Stored Successfully",Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener {
                Toast.makeText(context,"Error ${it.message}",Toast.LENGTH_LONG).show()
            }
    }

}