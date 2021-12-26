package com.tenTwenty.testapp.webServices


import com.tenTwenty.testapp.responseModel.imagesResponse.ImagesResponse
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {


    @GET("/3/movie/upcoming")
    fun upcommingMovie(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ):
            Call<UpcommingMovieResponse>

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

}
