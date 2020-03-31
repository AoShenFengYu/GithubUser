package com.aoshenfengyu.androidexercise

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.aoshenfengyu.androidexercise.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        binding?.activityMainViewPager?.adapter = viewPagerAdapter

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding!!.viewModel = viewModel

        binding!!.activityMainTabLayout.setupWithViewPager(binding?.activityMainViewPager!!)
    }

}
