package com.tenTwenty.testapp.ui.watch

import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.webServices.WebSerivces
import retrofit2.Response


class WatchRepo constructor(private val apiService: WebSerivces) {

    suspend fun getUpcomingMovies (apikey:String, page:Int): Response<UpcommingMovieResponse> = apiService.upcommingMovie(apikey,page)

}