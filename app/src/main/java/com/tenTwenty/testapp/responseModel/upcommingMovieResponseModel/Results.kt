package com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.annotations.SerializedName
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.appUtil.AppConstant

@Entity(
    tableName = "UP_Coming_Movie",
    indices = [Index(value = ["id"], unique = true)])
open class Results  {

    @SerializedName("adult")
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    var backdropPath: String? = null

    @Ignore
    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int> = arrayListOf()

    @PrimaryKey
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

           val options: RequestOptions = RequestOptions()
               .centerCrop()
               .placeholder(R.mipmap.image_place_holder)
               .error(R.mipmap.image_place_holder)
               .diskCacheStrategy(DiskCacheStrategy.ALL)
               .priority(Priority.HIGH)


           Glide.with(view.context).load(AppConstant.IMAGE_BASE_URL + url).apply(options).into(view)

       }
    }
}
