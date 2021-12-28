package com.tenTwenty.testapp.ui.movieDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


import androidx.lifecycle.liveData
import com.tenTwenty.testapp.genericModel.Resource
import com.tenTwenty.testapp.responseModel.imagesResponse.ImagesResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse


import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception

class MovieViewModel(private val repo:MovieDetailRepo) : ViewModel() {
    var list: LiveData<Resource<Response<ImagesResponse>>>? = null

        private fun getMovieDetails(apikey:String,page:Int) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data=repo.getMovieDetails(apikey,page)))

            }catch (exception: Exception){
                exception.printStackTrace()
                emit(Resource.error(data=null,message = exception.message?:"Error occured"))
            }
        }


    public fun getUpcomingMovie(apikey:String,page:Int): LiveData<Resource<Response<ImagesResponse>>>{

        if(list==null) {
            list = getMovieDetails(apikey, page)
        }

            return list!!
    }
    }

