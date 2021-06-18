package com.ibrahim.dev.mercado_ibra.home.presentation.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ibrahim.dev.mercado_ibra.commons.utils.TestObserver
import com.ibrahim.dev.mercado_ibra.commons.utils.test
import com.ibrahim.dev.mercado_ibra.home.domain.contract.CategoriesUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByCategoryUseCase
import com.ibrahim.dev.mercado_ibra.home.domain.contract.SearchByQueryUseCase
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var searchByCategoryUseCase: SearchByCategoryUseCase

    @Mock
    lateinit var searchByQueryUseCase: SearchByQueryUseCase

    @Mock
    lateinit var categoriesUseCase: CategoriesUseCase

    lateinit var viewModel: HomeViewModel

    private val observer: TestObserver<HomeEvents> by lazy {
        viewModel.homeEventsLiveData.test()
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(
            searchByCategoryUseCase,
            searchByQueryUseCase,
            categoriesUseCase
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request categories by sites success`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        Mockito.`when`(categoriesUseCase.getCategories(sitesCode))
            .thenReturn(FakeModels.mockResponseCategoriesUseCase())

        Mockito.`when`(searchByCategoryUseCase.search("MCO12345", "MCO"))
            .thenReturn(FakeModels.mockResponseSearchProduct())

        viewModel.getCategoriesBySites(sitesCode)

        observer.assertValue { it is HomeEvents.SuccessRequest }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request categories by sites fail`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        Mockito.`when`(categoriesUseCase.getCategories(sitesCode))
            .thenReturn(FakeModels.mockResponseCategoriesUseCaseError())

        viewModel.getCategoriesBySites(sitesCode)
        observer.assertValue { it is HomeEvents.ErrorRequest && it.msg == FakeModels.MSG_ERROR }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by category success`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val category = "MCO12345"

        Mockito.`when`(searchByCategoryUseCase.search(category, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProduct())

        viewModel.launchSearchByCategory(category, sitesCode)

        observer.assertValue { it is HomeEvents.SuccessRequest }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by category success but empty list`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val category = "MCO12345"

        Mockito.`when`(searchByCategoryUseCase.search(category, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProductEmptyResult())

        viewModel.launchSearchByCategory(category, sitesCode)

        observer.assertValue { it is HomeEvents.NotFountItems }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by category error`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val category = "MCO12345"

        Mockito.`when`(searchByCategoryUseCase.search(category, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProductFailRequest())

        viewModel.launchSearchByCategory(category, sitesCode)

        observer.assertValue { it is HomeEvents.ErrorRequest && it.msg == FakeModels.MSG_ERROR }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by query success`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val query = "xiamo"

        Mockito.`when`(searchByCategoryUseCase.search(query, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProduct())

        viewModel.launchSearchByCategory(query, sitesCode)

        observer.assertValue { it is HomeEvents.SuccessRequest }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by query success but empty list`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val query = "xiamo"

        Mockito.`when`(searchByCategoryUseCase.search(query, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProductEmptyResult())

        viewModel.launchSearchByCategory(query, sitesCode)

        observer.assertValue { it is HomeEvents.NotFountItems }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `request product by query error`() = coroutineScope.runBlockingTest {
        val sitesCode = "MCO"
        val query = "xiamo"

        Mockito.`when`(searchByCategoryUseCase.search(query, sitesCode))
            .thenReturn(FakeModels.mockResponseSearchProductFailRequest())

        viewModel.launchSearchByCategory(query, sitesCode)

        observer.assertValue { it is HomeEvents.ErrorRequest && it.msg == FakeModels.MSG_ERROR }
    }
}