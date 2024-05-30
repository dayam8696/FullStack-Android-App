package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fullstackapp.R
import com.example.fullstackapp.databinding.SaveFetchFragmentBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class SaveFetchFragment : BaseFragment() {
    private lateinit var binding: SaveFetchFragmentBinding
    private var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SaveFetchFragmentBinding.inflate(inflater, container, false)
        val zName = binding.textViewName
        val zAddress = binding.textViewAddress
        val zEmail = binding.textViewEmail
        val zPhone = binding.textViewPhoneNumber
        val tvName = binding.editTextName
        val tvAddress = binding.editTextAddress
        val tvEmail = binding.editTextEmail
        val tvPhone = binding.editTextPhoneNumber

        val userId = "dayam"
        if (userId.isNotEmpty()) {
            val ref = db.collection("users").document(userId)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val name = document.getString("name")
                        val address = document.getString("address")
                        val email = document.getString("email")
                        val phone = document.getString("phone")

                        zName.setText(name)
                        zAddress.setText(address)
                        zEmail.setText(email)
                        zPhone.setText(phone)

                        tvName.setText(name)
                        tvAddress.setText(address)
                        tvEmail.setText(email)
                        tvPhone.setText(phone)
                    } else {
                        showToast("No such document exists!")
                    }
                } else {
                    showToast("Failed to fetch data!")
                }
            }
                .addOnFailureListener { e ->
                    showToast("Failed to fetch data: ${e.message}")
                }
        } else {
            showToast("User not logged in!")
        }

        binding.buttonUpdateData.setOnClickListener {
            val sName = tvName.text.toString()
            val sAddress = tvAddress.text.toString()
            val sEmail = tvEmail.text.toString()
            val sPhone = tvPhone.text.toString()

            val updateMap = mapOf(
                "name" to sName,
                "address" to sAddress,
                "email" to sEmail,
                "phone" to sPhone
            )

            db.collection("users").document(userId).set(updateMap, SetOptions.merge())
                .addOnSuccessListener {
                    showToast("Successfully Updated")

                }
                .addOnFailureListener { e ->
                    showToast("Failed to update data: ${e.message}")
                }
        }

        return binding.root
    }


}
