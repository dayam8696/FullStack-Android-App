package com.example.fullstackapp.fragments

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.fullstackapp.R
import com.example.fullstackapp.adapter.RvContactsAdapter
import com.example.fullstackapp.databinding.UpdateFragmentBinding
import com.example.fullstackapp.models.Contacts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateFragment : BaseFragment() {
    private val binding by lazy{UpdateFragmentBinding.inflate(layoutInflater)}
    private lateinit var firebaseRef :DatabaseReference
    val args :UpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        firebaseRef = FirebaseDatabase.getInstance().getReference("contacts")

        binding.apply {
            edtName.setText(args.name)
            edtPhone.setText(args.phone)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSend.setOnClickListener {
            updateData()
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)
        }
    }

    private fun updateData() {
        val name = binding.edtName.text.toString()
        val phone = binding.edtPhone.text.toString()
        val contacts = Contacts(args.id ,name,phone)

        firebaseRef.child(args.id).setValue(contacts)
            .addOnCompleteListener {
                Toast.makeText(context,"Data Updated Successfully",Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener {
                Toast.makeText(context,"Error ${it.message}",Toast.LENGTH_LONG).show()
            }




    }
}