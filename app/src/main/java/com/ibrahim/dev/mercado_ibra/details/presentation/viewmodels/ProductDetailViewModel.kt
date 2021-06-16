package com.ibrahim.dev.mercado_ibra.details.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.details.domain.contract.ProductDetailUseCase
import com.ibrahim.dev.mercado_ibra.details.presentation.contract.DetailsEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCase: ProductDetailUseCase
) : ViewModel() {

    private val _eventsDetailsLiveData = MutableLiveData<DetailsEvent>()
    val eventsDetailsLiveData: LiveData<DetailsEvent> get() = _eventsDetailsLiveData

    fun getDetailProduct(productId: String) {
        viewModelScope.launch {
            useCase.getProductDetail(productId).collect { status ->
                when (status) {
                    is RequestStatus.Loading -> _eventsDetailsLiveData.value =
                        DetailsEvent.Loading(true)
                    is RequestStatus.Error -> {
                        _eventsDetailsLiveData.value = DetailsEvent.Loading(false)
                        _eventsDetailsLiveData.value = DetailsEvent.ErrorRequest(status.msg)
                    }
                    is RequestStatus.Success -> {
                        _eventsDetailsLiveData.value = DetailsEvent.Loading(false)
                        _eventsDetailsLiveData.value = DetailsEvent.SuccessRequest(status.value)
                    }

                }
            }
        }
    }
}