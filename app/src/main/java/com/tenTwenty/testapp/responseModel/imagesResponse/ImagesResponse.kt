package com.tenTwenty.testapp.responseModel.imagesResponse


import com.google.gson.annotations.SerializedName


data class ImagesResponse (

    @SerializedName("backdrops" ) var backdrops : ArrayList<Backdrops> = arrayListOf(),
    @SerializedName("id"        ) var id        : Int?                 = null,
    @SerializedName("logos"     ) var logos     : ArrayList<Logos>     = arrayListOf(),
    @SerializedName("posters"   ) var posters   : ArrayList<Posters>   = arrayListOf()

)