package com.ibrahim.dev.mercado_ibra.home.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.GeneralAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.commons.utils.hide
import com.ibrahim.dev.mercado_ibra.commons.utils.saveLaunch
import com.ibrahim.dev.mercado_ibra.commons.utils.show
import com.ibrahim.dev.mercado_ibra.commons.utils.showOrHide
import com.ibrahim.dev.mercado_ibra.databinding.FragmentHomeBinding
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
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
        viewModel.categoriesListLiveData.saveLaunch {
            viewModel.getCategoriesBySites(args.sitesCode)
        }
        initRecyclerView()
        observerLiveData()
        onClickListener()
    }

    private fun onClickListener() {
        with(binding) {
            editTextSearch.doAfterTextChanged {
                if (it.isNullOrEmpty()) {
                    recyclerViewCategories.show()
                    viewModel.launchSearchByCategory(
                        viewModel.lastCodeSelectedItem,
                        sitesCode = args.sitesCode
                    )
                } else {
                    recyclerViewCategories.hide()
                    viewModel.launchSearchByQuery(it.toString(), args.sitesCode)
                }
            }
        }
    }

    private fun observerLiveData() {
        viewModel.apply {
            homeEventsLiveData.observe(viewLifecycleOwner, { event ->
                when (event) {
                    is HomeEvents.SuccessRequest -> {
                        binding.apply {
                            recyclerView.show()
                            includeError.root.hide()
                        }
                        homeAdapter.submitList(event.listItem)
                    }
                    is HomeEvents.ErrorRequest -> {
                        binding.apply {
                            includeError.textViewErrorMsg.text = event.msg
                            rvGroup.hide()
                            includeError.root.show()
                        }
                    }
                    is HomeEvents.Loading -> binding.progressBar.showOrHide(event.isLoading)
                    is HomeEvents.NotFountItems -> {
                        binding.apply {
                            rvGroup.hide()
                            includeError.root.show()
                        }
                    }
                }
            })
            categoriesListLiveData.observe(viewLifecycleOwner, {
                categoryAdapter.submitList(mapToViewTypeCategories(it))
            })
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
            is EventsAdapter.SelectedItem -> handlerSelectedItemAdapter(events.pos, events.dataItem)
        }
    }

    private fun handlerSelectedItemAdapter(pos: Int, dataItem: ViewTypeVh) {
        when (dataItem) {
            is ViewTypeVh.ProductListBySearch -> {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                        dataItem.item.id
                    )
                )
            }
            is ViewTypeVh.ProductCategories -> {
                mapNewItemSelected(pos, dataItem.item)
                viewModel.launchSearchByCategory(dataItem.item.code, args.sitesCode)
            }
            else -> Unit
        }
    }

    private fun mapNewItemSelected(pos: Int, item: CategoriesModel) {
        var listAux: MutableList<CategoriesModel> = mutableListOf()
        viewModel.lastCodeSelectedItem = item.code
        viewModel.categoriesListLiveData.value?.let {
            listAux = it.toMutableList()
        }
        listAux.apply {
            removeAt(pos)
            add(0, item.copy(code = item.code, name = item.name, isSelected = true))
        }
        categoryAdapter.submitList(listAux.map { ViewTypeVh.ProductCategories(it) })
        listAux.clear()
    }
}