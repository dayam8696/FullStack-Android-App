package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fullstackapp.R
import com.example.fullstackapp.databinding.SaveDataFirestorFargmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SaveFragment : BaseFragment() {
    private val binding by lazy { SaveDataFirestorFargmentBinding.inflate(layoutInflater) }
    private var db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()  // FirebaseAuth instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.buttonSave.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            val sname = binding.editTextName.text.toString().trim()
            val sAddress = binding.editTextAddress.text.toString().trim()
            val sEmail = binding.editTextEmail.text.toString().trim()
            val sPhone = binding.editTextPhoneNumber.text.toString().trim()

            val userMap = hashMapOf(
                "name" to sname,
                "address" to sAddress,
                "email" to sEmail,
                "phone" to sPhone
            )

            // Get the current user's ID

                val userId = "dayam"

                // Save data to Firestore using the user ID as the document ID
                db.collection("users").document(userId).set(userMap)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            showToast("Successfully Added!")
                            binding.editTextName.text.clear()
                            binding.editTextEmail.text.clear()
                            binding.editTextAddress.text.clear()
                            binding.editTextPhoneNumber.text.clear()
                        } else {
                            showToast("Failed to add data!")
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                    .addOnFailureListener { e ->
                        showToast("Failed to add data: ${e.message}")
                        binding.progressBar.visibility = View.GONE
                    }

        }
        binding.buttontoview.setOnClickListener {
            findNavController().navigate(R.id.action_saveFragment_to_saveFetchFragment)
        }
        return binding.root
    }

}
