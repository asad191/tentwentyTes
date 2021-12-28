package com.tenTwenty.testapp.webServices


import com.tenTwenty.testapp.responseModel.imagesResponse.ImagesResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WebSerivces {


    @GET("/3/movie/upcoming")
    suspend fun upcommingMovie(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ):
            Response<UpcommingMovieResponse>

    @GET("/3/movie/{id}")
    fun movieDetails(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ):
            Call<UpcommingMovieResponse>

    @GET("3/movie/{id}/images")
    fun movieImages(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ):
            Call<ImagesResponse>

    @GET("3/movie/{id}")
    fun movieDetail(
        @Path("id") id: Int,
        @Query("api_key") key: String
    ):
           Response<ImagesResponse>



}
