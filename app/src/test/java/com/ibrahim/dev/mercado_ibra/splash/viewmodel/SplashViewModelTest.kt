package com.ibrahim.dev.mercado_ibra.splash.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibrahim.dev.mercado_ibra.utils.MainCoroutineScopeRule
import com.ibrahim.dev.mercado_ibra.commons.network.RequestStatus
import com.ibrahim.dev.mercado_ibra.commons.utils.TestObserver
import com.ibrahim.dev.mercado_ibra.commons.utils.test
import com.ibrahim.dev.mercado_ibra.splash.domain.contract.SplashSitesUseCase
import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel
import com.ibrahim.dev.mercado_ibra.splash.presentation.contract.SplashEvents
import com.ibrahim.dev.mercado_ibra.splash.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var useCase: SplashSitesUseCase

    lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = SplashViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request sites result success`() = coroutineScope.runBlockingTest {
        val observer: TestObserver<SplashEvents> = viewModel.eventsSplashLiveData.test()
        val sitesModel = SitesModel("COP", "MCO", "colombia")
        val listSites = listOf(sitesModel, sitesModel, sitesModel)
        val requestStatus: RequestStatus<List<SitesModel>> = RequestStatus.Success(listSites)
        Mockito.`when`(useCase.getSites()).thenReturn(flow {
            emit(requestStatus)
        })
        viewModel.getSites()
        advanceTimeBy(1000)
        observer.assertValueAt(0){ it is SplashEvents.SitesSuccess }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `request sites result Error`() = coroutineScope.runBlockingTest {
        val observer: TestObserver<SplashEvents> = viewModel.eventsSplashLiveData.test()
        val requestStatus: RequestStatus<List<SitesModel>> = RequestStatus.Error(404,"not found")
        Mockito.`when`(useCase.getSites()).thenReturn(flow {
            emit(requestStatus)
        })
        viewModel.getSites()
        observer.assertValueAt(0){ it is SplashEvents.ErrorCategoriesRequest }
    }
}