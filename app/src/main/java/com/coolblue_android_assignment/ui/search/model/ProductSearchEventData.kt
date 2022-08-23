package com.coolblue_android_assignment.ui.search.model

sealed class ProductSearchEventData {

    object LoadingState : ProductSearchEventData()

    class ProductSearchSuccessState(val products: List<ProductUiModel>) :
        ProductSearchEventData()

    object ErrorState : ProductSearchEventData()

    companion object {
        fun loading(): ProductSearchEventData = LoadingState

        fun productSearchSuccessState(products: List<ProductUiModel>): ProductSearchEventData =
            ProductSearchSuccessState(products)

        fun error(): ProductSearchEventData = ErrorState
    }
}