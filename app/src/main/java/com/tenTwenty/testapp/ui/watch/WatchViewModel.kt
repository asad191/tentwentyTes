package com.tenTwenty.testapp.ui.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


import androidx.lifecycle.liveData
import com.tenTwenty.testapp.genericModel.Resource
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.webServices.WebSerivces


import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import java.lang.Exception

class WatchViewModel(private val repo:WatchRepo) : ViewModel() {
    var list: LiveData<Resource<Response<UpcommingMovieResponse>>>? = null

        private fun getUpcomingMovies(apikey:String,page:Int) = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data=repo.getUpcomingMovies(apikey,page)))

            }catch (exception: Exception){
                exception.printStackTrace()
                emit(Resource.error(data=null,message = exception.message?:"Error occured"))
            }
        }


    public fun getUpcomingMovie(apikey:String,page:Int): LiveData<Resource<Response<UpcommingMovieResponse>>>{

        if(list==null) {
            list = getUpcomingMovies(apikey, page)
        }

            return list!!
    }
    }

