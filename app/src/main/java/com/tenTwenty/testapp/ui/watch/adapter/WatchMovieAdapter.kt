package com.tenTwenty.testapp.ui.watch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.WatchMovieRowBinding
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results

public  class WatchMovieAdapter(): RecyclerView.Adapter<WatchMovieAdapter.ViewHolder>() {

    var movies:ArrayList<Results> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val binding:WatchMovieRowBinding = WatchMovieRowBinding.inflate(inflater)
        return  ViewHolder(binding)
    }

    fun setMoviellST(movies:ArrayList<Results> ){
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    inner class ViewHolder(val binding:WatchMovieRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model:Results){
            with(binding) {
                binding.tvMovieTitle.text = model.title
                Glide.with(itemView.context).load(com.tenTwenty.testapp.appUtil.AppConstant.IMAGE_BASE_URL+model.backdropPath).into(binding.icMovieThum!!)

            }

        }



    }
}