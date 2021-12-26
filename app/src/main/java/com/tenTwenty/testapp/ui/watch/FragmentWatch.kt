package com.tenTwenty.testapp.ui.watch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.databinding.FragmentFeatureBinding


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentFeature : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

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

  return binding.root
    }

    private  fun setUpRev(){
        binding.rvWatch.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)


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
}