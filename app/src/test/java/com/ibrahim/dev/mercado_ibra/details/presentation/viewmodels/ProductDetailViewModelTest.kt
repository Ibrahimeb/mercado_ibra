package com.ibrahim.dev.mercado_ibra.details.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibrahim.dev.mercado_ibra.commons.utils.TestObserver
import com.ibrahim.dev.mercado_ibra.commons.utils.test
import com.ibrahim.dev.mercado_ibra.details.domain.contract.ProductDetailUseCase
import com.ibrahim.dev.mercado_ibra.details.presentation.contract.DetailsEvent
import com.ibrahim.dev.mercado_ibra.home.presentation.contract.HomeEvents
import com.ibrahim.dev.mercado_ibra.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class ProductDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var useCase: ProductDetailUseCase

    lateinit var viewModel: ProductDetailViewModel

    private val observer: TestObserver<DetailsEvent> by lazy {
        viewModel.eventsDetailsLiveData.test()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProductDetailViewModel(useCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request detail product success`() = coroutineScope.runBlockingTest {
        val productCode = "MCO12345"
        Mockito.`when`(useCase.getProductDetail(productCode))
            .thenReturn(FakeModules.getProductDetailModel(false))

        viewModel.getDetailProduct(productCode)

        observer.assertValue { it is DetailsEvent.SuccessRequest }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request detail product Error`() = coroutineScope.runBlockingTest {
        val productCode = "MCO12345"
        Mockito.`when`(useCase.getProductDetail(productCode))
            .thenReturn(FakeModules.getProductDetailModel(true))

        viewModel.getDetailProduct(productCode)

        observer.assertValue { it is DetailsEvent.ErrorRequest && it.msg == FakeModules.MSG_ERROR }
    }


}