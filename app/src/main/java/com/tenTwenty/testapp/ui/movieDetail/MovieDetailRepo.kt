package com.tenTwenty.testapp.ui.movieDetail

import com.tenTwenty.testapp.responseModel.imagesResponse.ImagesResponse
import com.tenTwenty.testapp.responseModel.movieDetail.MovieDetailsResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.webServices.WebSerivces
import retrofit2.Response


class MovieDetailRepo constructor(private val apiService: WebSerivces) {

    suspend fun getMovieDetails ( movieId:Int,apikey:String): Response<MovieDetailsResponse> = apiService.movieDetail(movieId,apikey)

}
