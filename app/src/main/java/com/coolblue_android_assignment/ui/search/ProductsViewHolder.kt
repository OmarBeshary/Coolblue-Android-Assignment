package com.coolblue_android_assignment.ui.search

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.coolblue_android_assignment.R
import com.coolblue_android_assignment.databinding.ItemProductBinding
import com.coolblue_android_assignment.extensions.loadUrl
import com.coolblue_android_assignment.ui.search.model.ProductUiModel

class ProductsViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: ProductUiModel) {
        with(binding) {
            productImageIv
                .loadUrl(model.imageUrl)
            productNameTv.text = model.name
            productRateRb.rating = model.reviewAverage
            productNOfReviewsTv.text =
                itemView.context.getString(R.string.n_of_reviews_format, model.reviewCount)
            productInfoTv.text = model.info
            productPriceTv.text =
                itemView.context.getString(R.string.float_format, model.price)
            if (model.has2ndDayDelivery)
                product2ndDayDeliveryTv.visibility = View.VISIBLE
        }
    }
}