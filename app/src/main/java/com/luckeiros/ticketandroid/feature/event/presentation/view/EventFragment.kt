package com.luckeiros.ticketandroid.feature.event.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.luckeiros.ticketandroid.R
import com.luckeiros.ticketandroid.base.BaseFragment
import com.luckeiros.ticketandroid.common.extension.gone
import com.luckeiros.ticketandroid.common.extension.hideKeyboard
import com.luckeiros.ticketandroid.common.extension.visible
import com.luckeiros.ticketandroid.common.view.binding.viewBinding
import com.luckeiros.ticketandroid.common.view.pagination.PaginationListener
import com.luckeiros.ticketandroid.databinding.FragmentEventBinding
import com.luckeiros.ticketandroid.feature.event.domain.Event
import com.luckeiros.ticketandroid.feature.event.presentation.state.EventState
import com.luckeiros.ticketandroid.feature.event.presentation.viewmodel.EventViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class EventFragment : BaseFragment() {

    private val viewModel: EventViewModel by viewModel()
    private val binding by viewBinding(FragmentEventBinding::bind)
    private val adapter: EventAdapter = EventAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_event, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        initRecyclerView()
        observeStates()
        setupSearchClick()
    }

    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EventState.Loading -> onLoading()
                is EventState.Error -> onError()
                is EventState.Success -> onSuccess(state)
            }
        }
    }

    private fun onLoading() {
        showLoader()
        hideRecyclerView()
        binding.rvEvent.gone()
    }

    private fun onError() {
        hideLoader()
        Log.e("TAG", "error")
    }

    private fun onSuccess(state: EventState.Success) {
        hideLoader()
        showRecyclerView()
        if (state.firstLoad) {
            setUpEventList(state.events)
        } else {
            addEventsToList(state.events)
            hidePaginationLoader()
        }
    }

    private fun showRecyclerView() {
        binding.rvEvent.visible()
    }

    private fun hideRecyclerView() {
        binding.rvEvent.gone()
    }

    private fun showPaginationLoader() {
        binding.pbPaginationLoader.visible()
    }

    private fun hidePaginationLoader() {
        binding.pbPaginationLoader.gone()
    }

    private fun initRecyclerView() {
        binding.rvEvent.apply {
            adapter = this@EventFragment.adapter
            val layoutManager = layoutManager as LinearLayoutManager
            addOnScrollListener(createPaginationListener(layoutManager))
        }
    }

    private fun createPaginationListener(layoutManager: LinearLayoutManager) =
        object : PaginationListener(layoutManager) {
            override fun isLoading(): Boolean = viewModel.paginationLoading.value ?: false
            override fun loadMoreItems() {
                viewModel.getMoreEvents(binding.etEvent.text.toString())
                showPaginationLoader()
            }
        }

    private fun setUpEventList(events: List<Event>) {
        adapter.submitList(events)
    }

    private fun addEventsToList(events: List<Event>) {
        adapter.addEvents(events)
    }

    private fun setupSearchClick() {
        binding.btSearch.setOnClickListener {
            it.hideKeyboard()
            viewModel.getEvents(binding.etEvent.text.toString())
        }
    }
}
