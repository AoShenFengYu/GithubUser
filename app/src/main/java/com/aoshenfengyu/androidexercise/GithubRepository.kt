package com.aoshenfengyu.androidexercise

import android.util.Log
import androidx.annotation.WorkerThread
import com.aoshenfengyu.androidexercise.request.RequestManager

class GithubRepository {

    companion object {
        const val TAG = "GithubRepository"
        const val LIMIT_OF_PAGE = 50

        /**
         * Wait for search result
         * **/
        const val STATUS_LOADING = 2

        const val STATUS_LOADED_SUCCESSFULLY = 3

        const val STATUS_ON_NO_RESULT = 4

        val instance = GithubRepository()
    }

    @WorkerThread
    fun loadSGithubUsers(offset: Int): List<GithubUser>? {
        val retrofit = RequestManager.getInstance().retrofit
        val call = retrofit
            .create(GithubApi::class.java)
            .fetchGithubUsers(offset)
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    return   data
                } else {
                    return  ArrayList<GithubUser>()
                }
            } else {
                return null
            }
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }

        return null
    }


}