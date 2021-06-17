package com.ibrahim.dev.mercado_ibra.details.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ibrahim.dev.mercado_ibra.R
import com.ibrahim.dev.mercado_ibra.commons.utils.hide
import com.ibrahim.dev.mercado_ibra.commons.utils.show
import com.ibrahim.dev.mercado_ibra.commons.utils.showOrHide
import com.ibrahim.dev.mercado_ibra.databinding.FragmentProductDetailBinding
import com.ibrahim.dev.mercado_ibra.details.domain.models.ProductDetailsModel
import com.ibrahim.dev.mercado_ibra.details.presentation.contract.DetailsEvent
import com.ibrahim.dev.mercado_ibra.details.presentation.viewmodels.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getDetailProduct(args.productId)
        liveDataObserver()
    }

    private fun liveDataObserver() {
        viewModel.eventsDetailsLiveData.observe(viewLifecycleOwner, { event ->
            when (event) {
                is DetailsEvent.Loading -> binding.progressBar.showOrHide(event.isLoading)
                is DetailsEvent.ErrorRequest -> {
                    binding.apply {
                        includeError.textViewErrorMsg.text = event.msg
                        successGruop.hide()
                        includeError.root.show()
                    }
                }
                is DetailsEvent.SuccessRequest -> setupData(event.product)
            }
        })
    }

    private fun setupData(item: ProductDetailsModel) {
        binding.apply {
            textViewTitle.text = item.title
            textViewSoldQuantity.text =
                String.format(getString(R.string.fragment_detail_sold_quantity), item.soldQuantity)
            textViewPrice.text = String.format(
                getString(R.string.price_concat),
                item.currencyId,
                item.price.toString()
            )
            textViewAvailableQuantity.text = String.format(
                getString(R.string.fragment_detail_available_quantity),
                item.availableQuantity
            )
            textViewFreeShippingLabel.showOrHide(item.freeShipping)
            imageViewMercadoPago.showOrHide(item.acceptsMercadoPago)


            textViewCity.text = item.city

            Glide.with(requireContext())
                .load(item.imageUrl)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(imageViewProductImg)
        }
    }
}