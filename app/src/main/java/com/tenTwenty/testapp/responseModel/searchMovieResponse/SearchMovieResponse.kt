package com.tenTwenty.testapp.responseModel.searchMovieResponse

import com.google.gson.annotations.SerializedName

data class SearchMovieResponse (

    @SerializedName("page"          ) var page         : Int?               = null,
    @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf(),
    @SerializedName("total_pages"   ) var totalPages   : Int?               = null,
    @SerializedName("total_results" ) var totalResults : Int?               = null
)