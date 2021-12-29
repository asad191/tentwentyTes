package com.tenTwenty.testapp.ui.movieDetail.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.databinding.GenresRowBinding

class GenereAdapter(): RecyclerView.Adapter<GenereAdapter.ViewHolder>() {

    val genres:ArrayList<com.tenTwenty.testapp.responseModel.movieDetail.Genres> = ArrayList()
val colorList: ArrayList<Int> = ArrayList()

    init {
        colorList.add(R.color.aqua)
        colorList.add(R.color.pink)
        colorList.add(R.color.dark_blue)
        colorList.add(R.color.orange)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(genere: ArrayList<com.tenTwenty.testapp.responseModel.movieDetail.Genres>){
        this.genres.clear()
        this.genres.addAll(genere)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding:GenresRowBinding = GenresRowBinding.inflate(inflater)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(genres.get(position))
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    inner class ViewHolder(val binding:GenresRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: com.tenTwenty.testapp.responseModel.movieDetail.Genres){

        with(binding){
            tvGenreName.text = model.name
            setUpColor()

        }
        }
        fun setUpColor(){
            if(adapterPosition <= colorList.size-1) {
                binding.cv.setCardBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        colorList.get(adapterPosition)
                    )
                )
            }
        }
        }



    }


