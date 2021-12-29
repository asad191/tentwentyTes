package com.tenTwenty.testapp.ui.watch

import android.content.Context
import androidx.lifecycle.LiveData
import com.tenTwenty.testapp.responseModel.movieDetail.MovieDetailsResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.roomdatabse.MoviesDatabase
import com.tenTwenty.testapp.ui.watch.localCache.UpComingDao
import com.tenTwenty.testapp.webServices.WebSerivces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class WatchRepo constructor(private val apiService: WebSerivces,context:Context) {
    var db: UpComingDao = MoviesDatabase.getDatabase(context).movieDao()



    fun getLocalUpComingMovie(): LiveData<List<Results>> {
        return db.allMovie
    }



    suspend fun getUpcomingMovies (apikey:String, page:Int): Response<UpcommingMovieResponse> = apiService.upcommingMovie(apikey,page)

}