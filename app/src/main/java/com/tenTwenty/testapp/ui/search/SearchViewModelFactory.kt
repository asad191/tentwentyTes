package com.tenTwenty.testapp.ui.search


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class SearchViewModelFactory(private val repo: SearchRepo) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if(modelClass.isAssignableFrom(SearchViewModel::class.java)){
                return SearchViewModel(repo) as T
            }

            throw IllegalArgumentException("Unknown class name")
        }
    }


