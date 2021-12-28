package com.tenTwenty.testapp.ui.movieDetail

import com.tenTwenty.testapp.responseModel.imagesResponse.ImagesResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.webServices.WebSerivces
import retrofit2.Response


class MovieDetailRepo constructor(private val apiService: WebSerivces) {

    suspend fun getMovieDetails (apikey:String, movieId:Int): Response<ImagesResponse> = apiService.movieDetail(movieId,apikey)

}
