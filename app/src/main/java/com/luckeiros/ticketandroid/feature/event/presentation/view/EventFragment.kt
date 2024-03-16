package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.base.BaseFragment
import com.luckeiros.ticketandroid.common.view.viewBinding
import com.luckeiros.ticketandroid.databinding.FragmentEventBinding

class EventFragment : BaseFragment() {

    private val binding by viewBinding(FragmentEventBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTest.text = "seti"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event, container, false)
}