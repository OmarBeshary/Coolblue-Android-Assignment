package com.coolblue_android_assignment.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.coolblue_android_assignment.databinding.ItemProductBinding
import com.coolblue_android_assignment.ui.search.model.ProductUiModel
import java.util.Collections.emptyList


class ProductsAdapter(private var list: MutableList<ProductUiModel> = emptyList()) :
    RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            binding = ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(model = list[holder.adapterPosition])
    }

    override fun getItemCount(): Int = list.size

    fun updateProductList(products: MutableList<ProductUiModel>) {
        list.apply {
            if (isEmpty())
                list = products
            else
                addAll(products)
        }
        notifyDataSetChanged()
    }

    fun clearList() {
        list.clear()
        notifyDataSetChanged()
    }

}