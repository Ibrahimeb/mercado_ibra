package com.ibrahim.dev.mercado_ibra.splash.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashCategoriesUseCase
import com.ibrahim.dev.mercado_ibra.splash.presentation.contract.SplashEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: SplashCategoriesUseCase
) : ViewModel() {

    private val _eventsSplashLiveData = MutableLiveData<SplashEvents>()
    val eventsSplashLiveData: LiveData<SplashEvents> get() = _eventsSplashLiveData

    fun getCategories() {
        viewModelScope.launch {
            useCase.getCategories().collect {
                try {
                    _eventsSplashLiveData.postValue(SplashEvents.CategoriesSuccess(it))
                } catch (e: Exception) {
                    _eventsSplashLiveData.postValue(SplashEvents.ErrorCategoriesRequest)
                }
            }
        }
    }
}