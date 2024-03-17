package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.base.BaseFragment
import com.luckeiros.ticketandroid.common.extension.observe
import com.luckeiros.ticketandroid.common.view.viewBinding
import com.luckeiros.ticketandroid.databinding.FragmentEventBinding
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState
import com.luckeiros.ticketandroid.feature.event.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventFragment : BaseFragment() {

    private val viewModel: EventViewModel by viewModel()
    private val binding by viewBinding(FragmentEventBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadEvent("manchester")
        observeStates()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event, container, false)

    private fun observeStates() {
        observe(viewModel.state) { state ->
            when (state) {
                is EventState.Loading -> showLoader()
                is EventState.Error -> {
                    hideLoader()
                }
                is EventState.Success -> {
                    showEventList(state.events)
                    hideLoader()
                }
            }
        }
    }

    private fun setUpEventList(events: List<EventItem>) {
        binding.rvEvent.adapter = EventAdapter(events)
    }

    private fun showEventList(events: List<EventItem>) {
        setUpEventList(events)
    }
}