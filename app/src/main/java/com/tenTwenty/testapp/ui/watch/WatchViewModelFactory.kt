package com.tenTwenty.testapp.ui.watch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class WatchViewModelFactory(private val repo: WatchRepo) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if(modelClass.isAssignableFrom(WatchViewModel::class.java)){
                return WatchViewModel(repo) as T
            }

            throw IllegalArgumentException("Unknown class name")
        }
    }


