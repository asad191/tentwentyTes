package com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import com.tenTwenty.testapp.appUtil.AppConstant


open class Results  {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null
    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = arrayListOf()
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("original_language")
    var originalLanguage: String? = null
    @SerializedName("original_title")
    var originalTitle: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("popularity")
    var popularity: Double? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("video")
    var video: Boolean? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    var voteCount: Int? = null

    companion object {

       @JvmStatic
       @BindingAdapter("imageUrl")
       fun loadImage(view: ImageView, url: String) {
           if(url != null) {
               Glide.with(view.context).load(AppConstant.IMAGE_BASE_URL + url).into(view)
           }
       }
    }
}
