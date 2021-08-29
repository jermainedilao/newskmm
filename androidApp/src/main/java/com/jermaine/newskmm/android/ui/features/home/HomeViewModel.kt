package com.jermaine.newskmm.android.ui.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jermaine.newskmm.android.ui.features.model.ArticleViewRep
import com.jermaine.newskmm.data.ArticleRepository
import com.jermaine.newskmm.domain.article.Article
import com.jermaine.newskmm.domain.core.Result

class HomeViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private val _articles by lazy {
        MutableLiveData<List<ArticleViewRep>>()
    }

    val articles: LiveData<List<ArticleViewRep>> = _articles

    private val _isRefreshing by lazy {
        MutableLiveData(false)
    }

    val isRefreshing: LiveData<Boolean> = _isRefreshing

    init {
        getArticles()
    }

    fun getArticles() {
        _isRefreshing.value = true
        articleRepository.getArticles { result ->
            if (result is Result.Success) {
                _articles.value = result.data.map(ArticleViewRep::fromDomain)
            } else {
                Log.e(TAG, "getArticles", (result as Result.Error).exception)
            }
            _isRefreshing.value = false
        }
    }
}
