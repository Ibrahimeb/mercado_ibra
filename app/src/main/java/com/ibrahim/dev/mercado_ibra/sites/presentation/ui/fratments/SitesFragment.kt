package com.ibrahim.dev.mercado_ibra.sites.presentation.ui.fratments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.dev.mercado_ibra.sites.presentation.viewmodels.SitesViewModel
import com.ibrahim.dev.mercado_ibra.commons.adapter.EventsAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.GeneralAdapter
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.databinding.FragmentSitesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SitesFragment : Fragment() {

    lateinit var binding: FragmentSitesBinding

    private val args: SitesFragmentArgs by navArgs()

    private val viewModel: SitesViewModel by viewModels()

    private val categoriesAdapter: GeneralAdapter by lazy {
        GeneralAdapter(::handlerAdapterEvents)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSitesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            adapter = categoriesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        categoriesAdapter.submitList(viewModel.mapListAdapterList(args.listSites.toList()))
    }


    private fun handlerAdapterEvents(events: EventsAdapter) {
        when (events) {
            is EventsAdapter.SelectedItem -> {
                val data =  (events.dataItem as ViewTypeVh.Sites).item
                findNavController().navigate(SitesFragmentDirections.actionCategoryFragmentToHomeFragment(data.id))
            }
        }
    }
}