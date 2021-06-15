package com.ibrahim.dev.mercado_ibra.home.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByQueryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import com.ibrahim.dev.mercado_ibra.home.presentation.contract.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchByCategoryUseCase: SearchByCategoryUseCase,
    private val searchByQueryUseCase: SearchByQueryUseCase
) : ViewModel() {

    private val _homeEventsLiveData = MutableLiveData<HomeEvents>()
    val homeEventsLiveData: LiveData<HomeEvents> get() = _homeEventsLiveData

    fun launchSearchByCategory(category: String) {
        viewModelScope.launch {
            searchByCategoryUseCase.search(category).collect(::handlerStatus)
        }
    }

    fun launchSearchByQuery(query: String){
        viewModelScope.launch {
            searchByQueryUseCase.search(query).collect(::handlerStatus)
        }
    }

    private fun handlerStatus(status: RequestStatus<List<ProductListModel>>){
        when (status) {
            is RequestStatus.Loading -> _homeEventsLiveData.value = HomeEvents.Loading(true)
            is RequestStatus.Error -> {
                _homeEventsLiveData.value = HomeEvents.Loading(false)
                _homeEventsLiveData.value = HomeEvents.ErrorRequest(status.msg)
            }
            is RequestStatus.Success -> {
                _homeEventsLiveData.value = HomeEvents.Loading(false)
                _homeEventsLiveData.value = HomeEvents.SuccessRequest(status.value.map {
                    ViewTypeVh.ProductListBySearch(
                        it
                    )
                })
            }
        }
    }
}