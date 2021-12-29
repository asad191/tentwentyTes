package com.tenTwenty.testapp.ui.watch

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


import androidx.lifecycle.liveData
import com.tenTwenty.testapp.genericModel.Resource
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.roomdatabse.MoviesDatabase
import com.tenTwenty.testapp.ui.watch.localCache.UpComingDao
import com.tenTwenty.testapp.webServices.WebSerivces


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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


    public fun getUpcomingMovieRemote(apikey:String, page:Int): LiveData<Resource<Response<UpcommingMovieResponse>>>{

        if(list==null) {
            list = getUpcomingMovies(apikey, page)
        }

            return list!!
    }

    suspend fun getLocalMovie()
        = liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data=repo.getLocalUpComingMovie()))

            }catch (exception: Exception){
                exception.printStackTrace()
                emit(Resource.error(data=null,message = exception.message?:"Error occured"))
            }
        }


    suspend fun insertMovie(database: MoviesDatabase?, list:ArrayList<Results>) {
        database?.let { db ->
            withContext(Dispatchers.IO) {
                val movieDao: UpComingDao = db.movieDao()
                movieDao.deleteAll()



                movieDao.insert(list)
            }
        }
    }



}

