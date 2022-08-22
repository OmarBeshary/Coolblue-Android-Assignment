package com.coolblue_android_assignment.di.module

import com.coolblue_android_assignment.di.FragmentScope
import com.coolblue_android_assignment.ui.search.SearchProductsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): SearchProductsFragment

}