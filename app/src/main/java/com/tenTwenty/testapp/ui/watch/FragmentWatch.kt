package com.tenTwenty.testapp.ui.watch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.FragmentFeatureBinding
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.UpcommingMovieResponse
import com.tenTwenty.testapp.ui.watch.adapter.WatchMovieAdapter
import com.tenTwenty.testapp.webServices.ApiInterface
import com.tenTwenty.testapp.webServices.RetrofitSingleTon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentFeature : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    private var   watchMovieAdapter:WatchMovieAdapter? = null
    private val featureMovies:ArrayList<Results> = arrayListOf()
    private val PAGE_START = 1
    var loading = true
    var pastVisiblesItems: Int =0
    var visibleItemCount: Int =0
    var totalItemCount: Int=0


    private val isLoading = false


    private val isLastPage = false


    private val TOTAL_PAGES = 3

    private var currentPage = 1

    private lateinit var binding:FragmentFeatureBinding

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
        // Inflate the layout for this fragment

        binding = FragmentFeatureBinding.inflate(inflater, container, false)
//        return inflater.inflate(R.layout.fragment_feature, container, false)
        setUpRev()
        upcomingMovieCall(currentPage)






  return binding.root
    }

    private  fun setUpRev(){

        val mLayoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvWatch.layoutManager = mLayoutManager

        watchMovieAdapter = WatchMovieAdapter()
        binding.rvWatch.adapter = watchMovieAdapter!!

        binding.rvWatch.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = mLayoutManager.getChildCount()
                    totalItemCount = mLayoutManager.getItemCount()
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            currentPage +=1
                            upcomingMovieCall(currentPage)

                            loading = true
                        }
                    }
                }
            }
        })

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


    fun upcomingMovieCall(page :Int){

        val request = RetrofitSingleTon.buildService(ApiInterface::class.java)
        val call = request.upcommingMovie(AppConstant.API_KEY,page)

        call.enqueue(object : Callback<UpcommingMovieResponse> {
            override fun onResponse(call: Call<UpcommingMovieResponse>, response: Response<UpcommingMovieResponse>) {
                if (response.isSuccessful){

                    if (!response.body()?.results!!.isEmpty()){
                        watchMovieAdapter?.setMoviellST(response.body()?.results!!)

                    }
                    else{

                        Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
                    }



                }

                else{
                    Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<UpcommingMovieResponse>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(context, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        })


    }



}