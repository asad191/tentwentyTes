package com.tenTwenty.testapp.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.databinding.SearchRowBinding
import com.tenTwenty.testapp.databinding.WatchMovieRowBinding
import com.tenTwenty.testapp.responseModel.searchMovieResponse.Results


class SearchAdapter(val listener: SearchListener): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    val movies:ArrayList<Results> = ArrayList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(movies:List<Results>){
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
         val binding:SearchRowBinding = SearchRowBinding.inflate(inflater)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(movies.get(position))
    }

    override fun getItemCount(): Int {
       return movies.size
    }

    inner class ViewHolder(val binding:SearchRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model:Results){

            binding.model = model
            itemView.setOnClickListener {
                if(adapterPosition != RecyclerView.NO_POSITION){
                    listener.onItemClicked(model)
                }
            }
        }
    }

    interface  SearchListener{

        fun onItemClicked(model:Results)
    }
}