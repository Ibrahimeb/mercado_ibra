package com.ibrahim.dev.mercado_ibra.splash.presentation.viewmodel

import androidx.lifecycle.*
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashSitesUseCase
import com.ibrahim.dev.mercado_ibra.splash.presentation.contract.SplashEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: SplashSitesUseCase
) : ViewModel() {

    private val _eventsSplashLiveData = MutableLiveData<SplashEvents>()
    val eventsSplashLiveData: LiveData<SplashEvents> get() = _eventsSplashLiveData

    fun getSites() {
        viewModelScope.launch {
            useCase.getSites().collect { status ->
                when (status){
                    is RequestStatus.Success -> {
                        delay(1000)
                        _eventsSplashLiveData.postValue(SplashEvents.SitesSuccess(status.value))
                    }
                    is RequestStatus.Error -> _eventsSplashLiveData.postValue(SplashEvents.ErrorCategoriesRequest)
                    else -> Unit
                }
            }
        }
    }
}