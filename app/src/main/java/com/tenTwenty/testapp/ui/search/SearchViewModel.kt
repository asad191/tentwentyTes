package com.tenTwenty.testapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


import androidx.lifecycle.liveData
import com.tenTwenty.testapp.genericModel.Resource
import com.tenTwenty.testapp.responseModel.movieDetail.MovieDetailsResponse
import com.tenTwenty.testapp.responseModel.searchMovieResponse.SearchMovieResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse


import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception

class SearchViewModel(private val repo:SearchRepo) : ViewModel() {
    var list: LiveData<Resource<Response<SearchMovieResponse>>>? = null

         fun getSearList(apikey:String,query: String) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data=repo.searchMovie(query,apikey)))

            }catch (exception: Exception){
                exception.printStackTrace()
                emit(Resource.error(data=null,message = exception.message?:"Error occured"))
            }
        }



    }

