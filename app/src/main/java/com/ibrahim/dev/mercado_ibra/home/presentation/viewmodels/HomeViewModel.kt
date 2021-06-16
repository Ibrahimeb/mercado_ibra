package com.ibrahim.dev.mercado_ibra.home.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.dev.mercado_ibra.commons.adapter.ViewTypeVh
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.home.domain.contract.CategoriesUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByQueryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.models.CategoriesModel
import com.ibrahim.dev.mercado_ibra.home.domain.models.ProductListModel
import com.ibrahim.dev.mercado_ibra.home.presentation.contract.HomeEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchByCategoryUseCase: SearchByCategoryUseCase,
    private val searchByQueryUseCase: SearchByQueryUseCase,
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {

    private val _homeEventsLiveData = MutableLiveData<HomeEvents>()
    val homeEventsLiveData: LiveData<HomeEvents> get() = _homeEventsLiveData

    private val _categorySelectedLiveData = MutableLiveData<CategoriesModel>()
    val categorySelectedLiveData: LiveData<CategoriesModel> get() = _categorySelectedLiveData

    fun getCategoriesBySites(sitesCode: String) {
        viewModelScope.launch {
            categoriesUseCase.getCategories(sitesCode).collect { status ->
                when (status) {
                    is RequestStatus.Loading -> _homeEventsLiveData.value = HomeEvents.Loading(true)
                    is RequestStatus.Error -> {
                        _homeEventsLiveData.value = HomeEvents.Loading(false)
                        _homeEventsLiveData.value = HomeEvents.ErrorRequest(status.msg)
                    }
                    is RequestStatus.Success -> searchItemByRandomCategory(status.value, sitesCode)
                }
            }
        }
    }

    private fun searchItemByRandomCategory(
        listCategories: List<CategoriesModel>,
        sitesCode: String
    ) {
        val randomIndex = (listCategories.indices).random()
        val randomItem = listCategories[randomIndex]
        launchSearchByCategory(randomItem.code, sitesCode)
        _categorySelectedLiveData.value = randomItem
        _homeEventsLiveData.value =
            HomeEvents.SuccessRequest(listCategories.map {item ->
                ViewTypeVh.ProductCategories(item)
            })
    }

    fun launchSearchByCategory(category: String, sitesCode: String) {
        viewModelScope.launch {
            searchByCategoryUseCase.search(category, sitesCode).collect(::handlerStatus)
        }
    }

    fun launchSearchByQuery(query: String, sitesCode: String) {
        viewModelScope.launch {
            searchByQueryUseCase.search(query, sitesCode).collect(::handlerStatus)
        }
    }

    private fun handlerStatus(status: RequestStatus<List<ProductListModel>>) {
        when (status) {
            is RequestStatus.Loading -> _homeEventsLiveData.value = HomeEvents.Loading(true)
            is RequestStatus.Error -> {
                _homeEventsLiveData.value = HomeEvents.Loading(false)
                _homeEventsLiveData.value = HomeEvents.ErrorRequest(status.msg)
            }
            is RequestStatus.Success -> {
                _homeEventsLiveData.value = HomeEvents.Loading(false)
                _homeEventsLiveData.value = if (status.value.isNullOrEmpty().not()) {
                    HomeEvents.SuccessRequest(status.value.map {
                        ViewTypeVh.ProductListBySearch(
                            it
                        )
                    })
                } else {
                    HomeEvents.NotFountItems
                }
            }
        }
    }
}