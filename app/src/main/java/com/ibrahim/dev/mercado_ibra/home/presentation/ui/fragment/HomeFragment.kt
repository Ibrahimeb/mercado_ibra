package com.ibrahim.dev.mercado_ibra.home.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.GeneralAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.commons.utils.showOrHide
import com.ibrahim.dev.mercado_ibra.databinding.FragmentHomeBinding
import com.ibrahim.dev.mercado_ibra.home.presentation.contract.HomeEvents
import com.ibrahim.dev.mercado_ibra.home.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    private val args: HomeFragmentArgs by navArgs()

    private val viewModel: HomeViewModel by viewModels()

    private val homeAdapter: GeneralAdapter by lazy {
        GeneralAdapter(::handlerAdapterEvents)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.launchSearchByCategory(args.categoriesCode)
        initRecyclerView()
        observerLiveData()
        onClickListener()
    }

    private fun onClickListener() {
        with(binding) {
            editTextSearch.doAfterTextChanged {
                if (it.isNullOrEmpty()) {
                    viewModel.launchSearchByCategory(args.categoriesCode)
                } else {
                    viewModel.launchSearchByQuery(it.toString())
                }
            }
        }
    }

    private fun observerLiveData() {
        viewModel.homeEventsLiveData.observe(viewLifecycleOwner, { event ->
            when (event) {
                is HomeEvents.SuccessRequest -> homeAdapter.submitList(event.listItem)
                is HomeEvents.ErrorRequest -> Toast.makeText(
                    requireContext(),
                    event.msg,
                    Toast.LENGTH_SHORT
                ).show()
                is HomeEvents.Loading -> binding.progressBar.showOrHide(event.isLoading)
            }
        })
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = homeAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun handlerAdapterEvents(events: EventsAdapter) {
        when (events) {
            is EventsAdapter.SelectedItem -> {
                val data = (events.dataItem as ViewTypeVh.ProductListBySearch)
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                        data.item.id
                    )
                )
            }
        }

    }
}