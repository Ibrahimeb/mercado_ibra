package com.ibrahim.dev.mercado_ibra.splash.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ibrahim.dev.mercado_ibra.categories.presentation.ui.fratments.CategoryFragment
import com.ibrahim.dev.mercado_ibra.databinding.FragmentSplashBinding
import com.ibrahim.dev.mercado_ibra.splash.presentation.contract.SplashEvents
import com.ibrahim.dev.mercado_ibra.splash.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        viewModel.getCategories()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventsSplashLiveData.observe(viewLifecycleOwner, { events ->
            when (events) {
                is SplashEvents.CategoriesSuccess -> findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToCategoryFragment(events.list.toTypedArray()))
                is SplashEvents.ErrorCategoriesRequest -> Toast.makeText(
                    requireContext(),
                    "todo mal",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}