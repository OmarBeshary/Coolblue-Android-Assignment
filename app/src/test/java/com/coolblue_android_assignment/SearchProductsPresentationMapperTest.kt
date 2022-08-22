package com.coolblue_android_assignment

import com.coolblue_android_assignment.ui.search.mapper.SearchProductsPresentationMapper
import com.domain.search.models.Product
import org.junit.Assert.assertEquals
import org.junit.Test


class SearchProductsPresentationMapperTest {

    @Test
    fun toProductUiModel_ProductInfoUiModelIsCorrect() {
        // Arrange:
        val product = Product(
            name = "Apple Iphone",
            imageUrl = "",
            price = 23.45f,
            info = listOf(
                "Intel HD Graphics 6000",
                "Intel Core i5",
                "Geschikt voor studie & onderweg"
            ),
            reviewCount = 13L,
            reviewAverage = 8.5f,
            canDeliver2ndDay = true
        )
        val productInfoUiModel =
            "Intel HD Graphics 6000\nIntel Core i5\nGeschikt voor studie & onderweg"

        // Act
        // :
        val productUiModel = SearchProductsPresentationMapper.toProductUiModel(product)

        //Assert:
        assertEquals(productInfoUiModel, productUiModel.info)
    }

    @Test
    fun toProductUiModel_ProductInfoUiModelIsEmpty() {
        // Arrange:
        val product = Product(
            name = "Apple Iphone",
            imageUrl = "",
            price = 23.45f,
            info = emptyList(),
            reviewCount = 13L,
            reviewAverage = 8.5f,
            canDeliver2ndDay = true
        )
        val productInfoUiModel = ""

        // Act
        // :
        val productUiModel = SearchProductsPresentationMapper.toProductUiModel(product)

        //Assert:
        assertEquals(productInfoUiModel, productUiModel.info)
    }

    @Test
    fun toProductUiModel_ProductReviewAverageUiModelIsCorrect() {
        // Arrange:
        val product = Product(
            name = "Apple Iphone",
            imageUrl = "",
            price = 23.45f,
            info = emptyList(),
            reviewCount = 13L,
            reviewAverage = 9.555f,
            canDeliver2ndDay = true
        )

        // Act:
        val productUiModel = SearchProductsPresentationMapper.toProductUiModel(product)

        //Assert:
        assertEquals(productUiModel.reviewAverage,4.7775f )
    }
}