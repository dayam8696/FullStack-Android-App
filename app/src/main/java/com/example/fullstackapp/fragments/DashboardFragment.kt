package com.example.fullstackapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.fullstackapp.R
import com.example.fullstackapp.databinding.DashboardFragmentBinding

class DashboardFragment : BaseFragment() {
    private val binding by lazy { DashboardFragmentBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.realtime.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_homeFragment)
        }

        binding.firestore.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_saveFragment)
        }
        return binding.root

    }
}