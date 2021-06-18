package com.ibrahim.dev.mercado_ibra.sites.presentation.viewmodels

import com.ibrahim.dev.mercado_ibra.splash.domain.models.SitesModel
import org.junit.Assert.assertTrue
import org.junit.Test

class SitesViewModelTest {
    private val viewModel by lazy {
        SitesViewModel()
    }

    @Test
    fun `map list item to Sites Adapter`() {
        val sitesModel = SitesModel("COP", "MCO", "colombia")
        val listSites = listOf(sitesModel, sitesModel, sitesModel)

        assertTrue(viewModel.mapListAdapterList(listSites).size > 0)
    }

    @Test
    fun `map list item to Sites Adapter parameter empty list`() {
        assertTrue(viewModel.mapListAdapterList(emptyList()).isEmpty())
    }

}