package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.base.BaseFragment
import com.luckeiros.ticketandroid.databinding.FragmentEventBinding

class EventFragment : BaseFragment() {

    private var binding: FragmentEventBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventBinding.inflate(inflater)
        return binding?.root
    }
}