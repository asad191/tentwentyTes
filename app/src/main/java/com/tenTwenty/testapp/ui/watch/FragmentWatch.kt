package com.tenTwenty.testapp.ui.watch

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.FragmentFeatureBinding
import com.tenTwenty.testapp.genericModel.Status
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results
import com.tenTwenty.testapp.ui.movieDetail.MovieDetailsActivity

import com.tenTwenty.testapp.ui.watch.adapter.WatchMovieAdapter
import com.tenTwenty.testapp.webServices.WebSerivces
import com.tenTwenty.testapp.webServices.RetrofitObject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentFeature : Fragment(), WatchMovieAdapter.WatchListener {
    lateinit var viewModel: WatchViewModel
    private var param1: String? = null
    private var param2: String? = null
    var loading = false
    private  var pageNo = 1

private lateinit var watchMovieAdapter: WatchMovieAdapter
    private lateinit var binding:FragmentFeatureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if(requireActivity()!=null){
            setupViewModel()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentFeatureBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_feature, container, false)
        setUpUi()




        setUpObserver()

  return binding.root
    }

    private  fun setUpUi(){
        val mlayoutManger = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvWatch.layoutManager = mlayoutManger
        watchMovieAdapter = WatchMovieAdapter(this)
        binding.rvWatch.adapter = watchMovieAdapter


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFeature().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun setupViewModel() {


        viewModel = ViewModelProvider(requireActivity(), WatchViewModelFactory(WatchRepo(RetrofitObject.buildService(WebSerivces::class.java),requireContext()))).get(WatchViewModel::class.java)


    }

    private fun setUpObserver() {

        viewModel.getUpcomingMovieRemote(AppConstant.API_KEY,pageNo).observe(requireActivity(), Observer {

            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        showProgress(false)
                        resource.data?.let { upcomingMvieResponse ->setDataToList(upcomingMvieResponse.body()!!?.results) }
                    }
                    Status.ERROR -> {
                        showProgress(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        showProgress(true)
                    }
                }

            }
        })
    }

    private fun setDataToList(movies: List<Results>) {
        if (movies.size > 0) {
            movies.forEach {
                watchMovieAdapter.setData(it)
            }
        }
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            binding.pbLoading.visibility = View.VISIBLE
        } else {
            binding.pbLoading.visibility = View.GONE
        }

}

    override fun onItemClicked(model: Results) {

        val  intent:Intent = Intent(requireContext(),MovieDetailsActivity::class.java)
        intent.putExtra("id",model.id)
        startActivity(intent)
    }


}