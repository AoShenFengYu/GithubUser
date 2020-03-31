package com.aoshenfengyu.androidexercise.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aoshenfengyu.androidexercise.*
import com.aoshenfengyu.androidexercise.databinding.FragmentMainAllUsersBinding
import com.bumptech.glide.Glide


class AllUsersFragment : Fragment() {
    companion object {

        fun newInstance(): AllUsersFragment {
            return AllUsersFragment()
        }

    }

    private var viewList = ArrayList<View>()

    private val mAttachListener = object : RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewAttachedToWindow(view: View) {
            viewList.add(view)
        }

        override fun onChildViewDetachedFromWindow(view: View) {
            Glide.with(view.context).clear(view)
            Glide.get(view.context).clearMemory()
            viewList.remove(view)
        }
    }

    private var binding: FragmentMainAllUsersBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main_all_users, container, false
        )
        val view: View = binding?.root!!
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding?.viewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MainAdapter(context!!)
        binding?.activityMainList?.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding?.activityMainList?.adapter = adapter

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val GithubUsersObserver = GithubUsersObserver(viewModel, adapter)
        viewModel.getGithubUsersLiveData().observe(this, GithubUsersObserver)
        viewModel.getStatusLiveData().observe(this, Observer { status ->
            when (status) {
                GithubRepository.STATUS_LOADING -> {
                }
                GithubRepository.STATUS_LOADED_SUCCESSFULLY -> {
                }
                GithubRepository.STATUS_ON_NO_RESULT -> {
                }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        viewList.forEach {
            Glide.with(it).clear(it)
        }
        viewList.clear()
        Glide.get(context!!).clearMemory()
    }

    class GithubUsersObserver(val viewModel: MainViewModel, val adapter: MainAdapter) :
        Observer<PagedList<GithubUser>> {
        var h = Handler()
        override fun onChanged(items: PagedList<GithubUser>?) {
            adapter.submitList(items)
            if (items!!.size > 0) {
                viewModel.setStatus(GithubRepository.STATUS_LOADED_SUCCESSFULLY)
            } else {
                viewModel.setStatus(GithubRepository.STATUS_ON_NO_RESULT)
            }
        }
    }
}