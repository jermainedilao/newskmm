package com.jermaine.newskmm.android.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jermaine.newskmm.data.ArticleRepository

class HomeViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                ArticleRepository()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}