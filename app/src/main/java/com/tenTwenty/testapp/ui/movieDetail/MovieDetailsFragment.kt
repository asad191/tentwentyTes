package com.tenTwenty.testapp.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.ui.watch.WatchViewModelFactory
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.genericModel.Status
import com.tenTwenty.testapp.ui.watch.WatchRepo
import com.tenTwenty.testapp.ui.watch.WatchViewModel
import com.tenTwenty.testapp.webServices.RetrofitSingleTon
import com.tenTwenty.testapp.webServices.WebSerivces


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieDetailsFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: WatchViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setupViewModel() {


        viewModel = ViewModelProvider(requireActivity(), WatchViewModelFactory(
            WatchRepo(
                RetrofitSingleTon.buildService(WebSerivces::class.java))
        )
        ).get(WatchViewModel::class.java)
//

    }

    private fun setUpObserver() {

        viewModel.getUpcomingMovie(AppConstant.API_KEY,33).observe(requireActivity(), Observer {

            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                       // showProgress(false)
                       // resource.data?.let { upcomingMvieResponse ->setDataToList(upcomingMvieResponse.body()!!?.results) }
                    }
                    Status.ERROR -> {
                      //  showProgress(false)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        //showProgress(true)
                    }
                }

            }
        })
    }

//    private fun setDataToList(users: List<Results>) {
//        if (users.size > 0) {
//            users.forEach {
//                watchMovieAdapter.setData(it)
//            }
//        }
//    }
//
//    private fun showProgress(status: Boolean) {
//        if (status) {
//            binding.pbLoading.visibility = View.VISIBLE
//        } else {
//            binding.pbLoading.visibility = View.GONE
//        }
//
//    }
}