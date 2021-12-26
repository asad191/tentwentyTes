package com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel

import com.google.gson.annotations.SerializedName
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Dates
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results

data class UpcommingMovieResponse (

    @SerializedName("dates"         ) var dates        : Dates?             = Dates(),
    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null

)