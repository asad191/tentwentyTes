package com.tenTwenty.testapp.ui.search

import com.tenTwenty.testapp.responseModel.movieDetail.MovieDetailsResponse
import com.tenTwenty.testapp.responseModel.searchMovieResponse.SearchMovieResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.webServices.WebSerivces
import retrofit2.Response


class SearchRepo constructor(private val apiService: WebSerivces) {

    suspend fun searchMovie (query:String,apikey:String): Response<SearchMovieResponse> = apiService.movieSearch(query,apikey)

}