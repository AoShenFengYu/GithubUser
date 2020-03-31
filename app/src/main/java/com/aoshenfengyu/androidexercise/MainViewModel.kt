package com.aoshenfengyu.androidexercise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class MainViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * List
     * **/
    private var totalGithubUsersLiveData: LiveData<PagedList<GithubUser>>? = null
    private val dataSourceFactory = GithubUsersPagingDataSource.Factory()

    /**
     * Status
     * **/
    private val statusLiveData = MutableLiveData<Int>()

    fun getGithubUsersLiveData(): LiveData<PagedList<GithubUser>> {
        if (totalGithubUsersLiveData == null) {
            val pagedListConfig = PagedList.Config.Builder()
                .setPrefetchDistance(10)
                .build()
            totalGithubUsersLiveData = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
                .build()
        }
        return totalGithubUsersLiveData as LiveData<PagedList<GithubUser>>
    }

    fun setStatus(status: Int) {
        if (status == GithubRepository.STATUS_ON_NO_RESULT) {
            return
        }
        statusLiveData.postValue(status)
    }

    fun getStatusLiveData(): MutableLiveData<Int> {
        return statusLiveData
    }


}
