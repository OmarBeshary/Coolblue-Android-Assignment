package com.coolblue_android_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coolblue_android_assignment.ui.search.SearchProductsFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = supportFragmentManager;

        manager.beginTransaction().apply {
            add(
                R.id.fragment_container,
                SearchProductsFragment.newInstance(),
                SearchProductsFragment::class.java.name
            );
            addToBackStack(null);
            commit();
        }
    }
}