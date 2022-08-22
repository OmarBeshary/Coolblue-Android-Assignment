package com.coolblue_android_assignment.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coolblue_android_assignment.di.ViewModelFactory
import com.coolblue_android_assignment.di.ViewModelKey
import com.coolblue_android_assignment.ui.search.SearchProductsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchProductsViewModel::class)
    internal abstract fun bindSearchProductsViewModel(homeViewModel: SearchProductsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}