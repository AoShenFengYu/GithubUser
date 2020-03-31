package com.aoshenfengyu.androidexercise.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.aoshenfengyu.androidexercise.GithubApi
import com.aoshenfengyu.androidexercise.GithubUser
import com.aoshenfengyu.androidexercise.MainViewModel
import com.aoshenfengyu.androidexercise.R
import com.aoshenfengyu.androidexercise.databinding.FragmentMainMyInfoBinding
import com.aoshenfengyu.androidexercise.request.RequestManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyInfoFragment : Fragment() {
    companion object {
        const val TAG = "MyInfoFragment"
        fun newInstance(): MyInfoFragment {
            return MyInfoFragment()
        }

    }

    private var binding: FragmentMainMyInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_my_info, container, false
        )
        val view: View = binding?.root!!
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = RequestManager.getInstance().retrofit
        val call = retrofit
            .create(GithubApi::class.java)
            .fetchGithubUser(
                "aoshenfengyu"

            ).enqueue(object : Callback<GithubUser> {
                override fun onFailure(
                    call: Call<GithubUser>,
                    t: Throwable
                ) {
                    Log.e(
                        TAG,
                        "uploadAudio // onFailure // Error message : " + t.message
                    )
                }

                override fun onResponse(
                    call: Call<GithubUser>,
                    response: Response<GithubUser>
                ) {
                    Log.d(
                        TAG,
                        "uploadAudio // onResponse // response : $response"
                    )

                    val result = response.body()
                    if (result != null) {
                        onBind(result)
                    }
                }

            })


    }

    private fun onBind(githubUser: GithubUser) {
        binding?.myInfo = githubUser


        Glide.with(context!!)
            .load(githubUser.avatar_url)
            .placeholder(R.drawable.ic_photo_placeholder)
            .apply(
                RequestOptions()
                    .skipMemoryCache(true)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .fitCenter()
            )
            .into(binding?.fragmentMainMyInfoImage!!)

    }
}