package com.tenTwenty.testapp.ui.movieDetail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class MovieDetailViewModelFactory(private val repo: MovieDetailRepo) : ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
                return MovieViewModel(repo) as T
            }

            throw IllegalArgumentException("Unknown class name")
        }
    }


