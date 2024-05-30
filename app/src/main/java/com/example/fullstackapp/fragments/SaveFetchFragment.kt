package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fullstackapp.databinding.SaveFetchFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SaveFetchFragment : BaseFragment() {
    private val binding by lazy { SaveFetchFragmentBinding.inflate(layoutInflater) }
    private var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tvName = binding.editTextName
        val tvAddress = binding.editTextAddress
        val tvEmail = binding.editTextEmail
        val tvPhone = binding.editTextPhoneNumber

        val userId = "dayam"
        if (userId != null) {
            val ref = db.collection("users").document(userId)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val name = document.getString("name")
                        val address = document.getString("address")
                        val email = document.getString("email")
                        val phone = document.getString("phone")

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

        return binding.root
    }
}
