package com.tenTwenty.testapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.tenTwenty.testapp.R
import com.tenTwenty.testapp.databinding.ActivityMainBinding
import com.tenTwenty.testapp.ui.watch.FragmentFeature

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavigation()

        //DashboardFragment
        setCurrentFragment(FragmentFeature())

    }

    private  fun setUpNavigation(){
        val radius = resources.getDimension(R.dimen.cornerSize)

        val shapeDrawable : MaterialShapeDrawable = binding.bottomNnavigatinview.background as MaterialShapeDrawable
        shapeDrawable.shapeAppearanceModel = shapeDrawable.shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
        binding.bottomNnavigatinview.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dashboard->setCurrentFragment(FragmentFeature())
                R.id.watch->setCurrentFragment(FragmentFeature())
                R.id.library->setCurrentFragment(FragmentFeature())
                R.id.media_more->setCurrentFragment(FragmentFeature())

            }
            true
        }
    }


    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}