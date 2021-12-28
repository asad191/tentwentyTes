package com.tenTwenty.testapp.ui.watch.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.WatchMovieRowBinding
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results

class WatchMovieAdapter(): RecyclerView.Adapter<WatchMovieAdapter.ViewHolder>() {

    val movies:ArrayList<Results> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies:Results){
        this.movies.add(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val binding:WatchMovieRowBinding = WatchMovieRowBinding.inflate(inflater)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    inner class ViewHolder(val binding:WatchMovieRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model:Results){
            binding.model = model

//            with(binding){
//            tvVideoTitle.text = model.title
//
//                Glide.with(itemView.context).load(AppConstant.IMAGE_BASE_URL+model.backdropPath).into(ivThumbnail)
//
//            }
        }
    }
}