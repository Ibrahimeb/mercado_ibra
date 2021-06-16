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
import com.ibrahim.dev.mercado_ibra.commons.utils.makeToast
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

    private val categoryAdapter: GeneralAdapter by lazy {
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
        viewModel.getCategoriesBySites(args.sitesCode)
        initRecyclerView()
        observerLiveData()
        onClickListener()
    }

    private fun onClickListener() {
        with(binding) {
            editTextSearch.doAfterTextChanged {
                if (it.isNullOrEmpty()) {
                    viewModel.launchSearchByCategory(
                        viewModel.categorySelectedLiveData.value?.code.orEmpty(),
                        sitesCode = args.sitesCode
                    )
                } else {
                    viewModel.launchSearchByQuery(it.toString(), args.sitesCode)
                }
            }
        }
    }

    private fun observerLiveData() {
        viewModel.homeEventsLiveData.observe(viewLifecycleOwner, { event ->
            when (event) {
                is HomeEvents.SuccessRequest -> handlerSuccessData(event.listItem)
                is HomeEvents.ErrorRequest -> Toast.makeText(
                    requireContext(),
                    event.msg,
                    Toast.LENGTH_SHORT
                ).show()
                is HomeEvents.Loading -> binding.progressBar.showOrHide(event.isLoading)
                HomeEvents.NotFountItems -> requireContext().makeToast("no se encontro item")
            }
        })
    }

    private fun handlerSuccessData(viewTypeVh: List<ViewTypeVh>) {
        when (viewTypeVh[0]) {
            is ViewTypeVh.ProductCategories -> categoryAdapter.submitList(viewTypeVh)
            is ViewTypeVh.ProductListBySearch -> homeAdapter.submitList(viewTypeVh)
            else -> Unit
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.apply {
                adapter = homeAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            recyclerViewCategories.apply {
                adapter = categoryAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun handlerAdapterEvents(events: EventsAdapter) {
        when (events) {
            is EventsAdapter.SelectedItem -> handlerSelectedItemAdapter(events.dataItem)
        }
    }

    private fun handlerSelectedItemAdapter(dataItem: ViewTypeVh) {
        when (dataItem) {
            is ViewTypeVh.ProductListBySearch -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                        dataItem.item.id
                    )
                )
            }
            is ViewTypeVh.ProductCategories -> {
                viewModel.launchSearchByCategory(dataItem.item.code, args.sitesCode)
            }
            else -> Unit
        }
    }
}