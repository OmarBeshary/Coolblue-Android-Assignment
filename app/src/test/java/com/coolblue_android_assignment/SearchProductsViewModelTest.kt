package com.coolblue_android_assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.coolblue_android_assignment.ui.search.SearchProductsViewModel
import com.coolblue_android_assignment.ui.search.mapper.SearchProductsPresentationMapper
import com.coolblue_android_assignment.ui.search.model.ProductSearchEventData
import com.domain.search.models.SearchProductDomain
import com.domain.search.repo.SearchProductsRepository
import com.domain.search.use_cases.SearchProductsByPageUseCase
import com.domain.search.use_cases.SearchProductsUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

// Test viewModel
class SearchProductsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var observer: Observer<ProductSearchEventData>


    @MockK
    private lateinit var searchProductsUseCase: SearchProductsUseCase
    @MockK
    private lateinit var searchProductsByPageUseCase: SearchProductsByPageUseCase

    private lateinit var viewModel: SearchProductsViewModel


    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel = SearchProductsViewModel(searchProductsUseCase, searchProductsByPageUseCase)
        viewModel.observeSearchProduct().observeForever(observer)
    }


    @Test
    fun searchProduct_WhenError_ShouldPushErrorEvent() {
        // Arrange:
        val query = "iphone"
        every { searchProductsUseCase.invoke(query) } returns Single.error(Throwable())
        val loading = ProductSearchEventData.LoadingState
        val errorState = ProductSearchEventData.ErrorState

        //Act:
        viewModel.searchProduct(query)

        //Assert:
        verifySequence {
            observer.apply {
                onChanged(loading)
                onChanged(errorState)
            }
        }
    }

    @Test
    fun canLoadNextPage_WhenCurrentPageEqualsPageCount_ShouldReturnFalse() {
        // Arrange:
        viewModel.currentPage = 3
        viewModel.pageCount = 3

        // Act:
        val canLoad = viewModel.canLoadNextPage(
            visibleItemCount = 15,
            pastVisibleItems = 20,
            totalItemCount = 20
        )

        // Assert:
        assertFalse(canLoad)
    }

    @Test
    fun canLoadNextPage_WhenPastAndVisibleItemsSumLessThanTotalItems_ShouldReturnFalse() {
        // Arrange:
        viewModel.currentPage = 2
        viewModel.pageCount = 3

        // Act:
        val canLoad = viewModel.canLoadNextPage(
            visibleItemCount = 5,
            pastVisibleItems = 10,
            totalItemCount = 30
        )

        // Assert:
        assertFalse(canLoad)
    }
}