package com.tenTwenty.testapp.ui.watch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.databinding.WatchMovieRowBinding
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results

class WatchMovieAdapter(val movies:ArrayList<Results>): RecyclerView.Adapter<WatchMovieAdapter.ViewHolder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val binding:WatchMovieRowBinding = WatchMovieRowBinding.inflate(inflater)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
       return movies.size
    }

    inner class ViewHolder(binding:WatchMovieRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model:Results){

        }
    }
}