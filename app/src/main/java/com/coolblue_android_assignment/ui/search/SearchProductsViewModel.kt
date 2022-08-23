package com.coolblue_android_assignment.ui.search

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.coolblue_android_assignment.BaseViewModel
import com.coolblue_android_assignment.ui.search.mapper.SearchProductsPresentationMapper
import com.coolblue_android_assignment.ui.search.model.ProductSearchEventData
import com.domain.search.use_cases.SearchProductsByPageUseCase
import com.domain.search.use_cases.SearchProductsUseCase
import javax.inject.Inject

class SearchProductsViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val searchProductsByPageUseCase: SearchProductsByPageUseCase
) : BaseViewModel() {

    private val searchProductsScreenStates: MutableLiveData<ProductSearchEventData> =
        MutableLiveData()

    @VisibleForTesting
    var currentPage = 0

    @VisibleForTesting
    var pageCount = 0

    fun searchProduct(query: String) {
        executeSingle(source = searchProductsUseCase(query),
            loadingConsumer = {
                searchProductsScreenStates.value = ProductSearchEventData.loading()
            },
            successConsumer = { model ->
                val searchProductsScreen =
                    SearchProductsPresentationMapper.toSearchProductsPresentation(
                        searchProductDomain = model
                    )
                currentPage = 1
                pageCount = searchProductsScreen.pageCount
                searchProductsScreenStates.value = ProductSearchEventData.productSearchSuccessState(
                    products = searchProductsScreen.products
                )
            }, throwableConsumer = {})
    }

    fun canLoadNextPage(
        visibleItemCount: Int,
        pastVisibleItems: Int,
        totalItemCount: Int
    ) = visibleItemCount + pastVisibleItems >= totalItemCount && currentPage < pageCount


    fun getNextPage(query: String) {
        val nextPage = (currentPage + 1).toString()
        executeSingle(source = searchProductsByPageUseCase(
            query = query,
            nextPage = nextPage
        ),
            loadingConsumer = {
                searchProductsScreenStates.value = ProductSearchEventData.loading()
            }, successConsumer = { model ->
                val searchProductsScreen =
                    SearchProductsPresentationMapper.toSearchProductsPresentation(
                        searchProductDomain = model
                    )
                currentPage = searchProductsScreen.currentPage
                searchProductsScreenStates.value =
                    ProductSearchEventData.productSearchSuccessState(
                        products = searchProductsScreen.products
                    )

            }, throwableConsumer = {

            })
    }

    fun observeSearchProduct(): LiveData<ProductSearchEventData> = searchProductsScreenStates
}