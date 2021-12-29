package com.tenTwenty.testapp.ui.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tenTwenty.testapp.ui.watch.WatchViewModelFactory
import com.tenTwenty.testapp.appUtil.AppConstant
import com.tenTwenty.testapp.databinding.FragmentMovieSearchBinding
import com.tenTwenty.testapp.genericModel.Status
import com.tenTwenty.testapp.ui.watch.WatchRepo

import com.tenTwenty.testapp.webServices.RetrofitObject
import com.tenTwenty.testapp.webServices.WebSerivces
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.responseModel.searchMovieResponse.Results
import com.tenTwenty.testapp.ui.movieDetail.MovieDetailsActivity


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MovieSearchFragment : Fragment(),SearchAdapter.SearchListener {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModel: SearchViewModel
    lateinit var searchAdapter :SearchAdapter
    lateinit var  binding:FragmentMovieSearchBinding
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
        binding = FragmentMovieSearchBinding.inflate(layoutInflater)
        setupViewModel()
        setUpuI()

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MovieSearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun showInputMethod(view: View) {
        val imm: InputMethodManager? =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (imm != null) {
            imm.showSoftInput(view, 0)
        }
    }

    fun setUpuI(){
        searchAdapter = SearchAdapter(this)
        binding.rvSearch.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvSearch.adapter = searchAdapter

        //sv
        binding.sv.onActionViewExpanded()
        binding.sv.setFocusable(true);

        binding.sv.setOnQueryTextFocusChangeListener(OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                showInputMethod(view.findFocus())
            }
        })


        binding.sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                callSearch(newText)

                return true
            }

            fun callSearch(query: String?) {
                query?.let { setUpObserver(it) }
            }
        })
    }

    private fun setupViewModel() {


        viewModel = ViewModelProvider(requireActivity(), SearchViewModelFactory(
            SearchRepo(
                RetrofitObject.buildService(WebSerivces::class.java))
        )
        ).get(SearchViewModel::class.java)
//

    }


    private fun setUpObserver(query:String) {

        viewModel.getSearList(AppConstant.API_KEY,query).observe(requireActivity(), Observer {

            it?.let { resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                      showProgress(false)
                      resource.data?.let { searchRes ->setDataToList(searchRes.body()!!.results) }
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

    private fun setDataToList(result: List<Results>) {

        if (result.size > 0) {
            searchAdapter.setData(result)
        }
    }

    override fun onItemClicked(model: Results) {
        val  intent: Intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        intent.putExtra("id",model.id)
        startActivity(intent)
    }

    private fun showProgress(status: Boolean) {
        if (status) {
            binding.pb.visibility = View.VISIBLE
        } else {
            binding.pb.visibility = View.GONE
        }

    }
}