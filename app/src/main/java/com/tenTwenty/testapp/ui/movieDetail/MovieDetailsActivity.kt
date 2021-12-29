package com.tenTwenty.testapp.ui.movieDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.ActivityMovieDetailsBinding
import com.tenTwenty.testapp.genericModel.Status
import com.tenTwenty.testapp.responseModel.movieDetail.MovieDetailsResponse
import com.tenTwenty.testapp.webServices.RetrofitObject
import com.tenTwenty.testapp.webServices.WebSerivces

import android.os.Build
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.ui.movieDetail.adapter.GenereAdapter
import com.tenTwenty.testapp.ui.player.VideoPlayerFragment
import java.util.*


class MovieDetailsActivity : AppCompatActivity() {
    lateinit var viewModel: MovieViewModel
    lateinit var binding:ActivityMovieDetailsBinding
     var movieId:Int? = null
    lateinit var genAdapter: GenereAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController!!.hide(
                android.view.WindowInsets.Type.statusBars()
            )
        }

        setContentView(binding.root)


        if(intent.hasExtra("id")){

            movieId = intent.getIntExtra("id",0)
            binding.callback = this
            setUpUI()
            setupViewModel()
            setUpObserver(movieId!!)
        }
        else{
            finish()
        }


    }

private  fun setUpUI(){
    genAdapter = GenereAdapter()
    binding.rvGenre.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
    binding.rvGenre.adapter = genAdapter
}
    private fun setupViewModel() {


        viewModel = ViewModelProvider(this@MovieDetailsActivity, MovieDetailViewModelFactory(
            MovieDetailRepo(
                RetrofitObject.buildService(WebSerivces::class.java))
        )
        ).get(MovieViewModel::class.java)


    }

    private fun setUpObserver(id:Int) {

        viewModel.getMovieDetailMovie(AppConstant.API_KEY,id).observe(this@MovieDetailsActivity, Observer {

            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        // showProgress(false)
                     resource.data?.let { movieDetail ->setData(movieDetail.body()!!) }
                    }
                    Status.ERROR -> {
                        //  showProgress(false)
                        Toast.makeText(this@MovieDetailsActivity, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        //showProgress(true)
                    }
                }

            }
        })
    }

    public  fun onGeTicketsClick(view: View,id:Int){

    }

    public  fun onWatchClick(view: View,id:Int){
        playVideo(VideoPlayerFragment.newInstance("url"))
        //playVideo(VideoPlayerFragment.newInstance("url"))

    }
    public  fun onBackClick(view: View){
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    private  fun setData(mdata:MovieDetailsResponse){
        binding.model = mdata

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.image_place_holder)
            .error(R.mipmap.image_place_holder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
        Glide.with(this).load(AppConstant.IMAGE_BASE_URL+mdata.posterPath).apply(options).into(binding.movieImage)

        genAdapter.setData(mdata.genres)

    }

    private fun playVideo(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container,fragment).addToBackStack("vide")
            commit()
        }
}