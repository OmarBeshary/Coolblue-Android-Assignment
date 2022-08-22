package com.coolblue_android_assignment.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coolblue_android_assignment.databinding.FragmentSearchProductsBinding
import com.coolblue_android_assignment.extensions.hideKeyboard
import com.coolblue_android_assignment.ui.search.model.ProductSearchEventData
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class SearchProductsFragment : Fragment() {


    private lateinit var binding: FragmentSearchProductsBinding
    private val productsAdapter = ProductsAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchProductsViewModel

    private var query: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchProductsViewModel::class.java]
        initUi()
        initObservers()
    }

    private fun initUi() {
        with(binding) {
            searchEt.apply {
                setOnEditorActionListener { _, actionId, _ ->
                    val text = text.toString()
                    return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH &&
                        text.isEmpty().not()
                    ) {
                        hideKeyboard()
                        productsAdapter.clearList()
                        query = text
                        viewModel.searchProduct(query = query)
                        true
                    } else false
                }
            }
            productSearchRv.apply {
                val manager = LinearLayoutManager(requireContext())
                layoutManager = manager
                adapter = productsAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        if (dy > 0) {
                            with(manager) {
                                if (viewModel.canLoadNextPage(
                                        visibleItemCount = childCount,
                                        totalItemCount = itemCount,
                                        pastVisibleItems = findFirstVisibleItemPosition()
                                    )
                                )
                                    getNextSearchResult()
                            }
                        }

                    }
                })
            }
        }
    }

    private fun initObservers() {
        viewModel.observeSearchProduct().observe(viewLifecycleOwner) { event ->
            if (event is ProductSearchEventData.ProductSearchSuccessState)
                productsAdapter.updateProductList(products = event.products.toMutableList())
        }
    }

    private fun getNextSearchResult() {
        viewModel.getNextPage(query = query)
    }


    companion object {
        fun newInstance() = SearchProductsFragment()
    }

}