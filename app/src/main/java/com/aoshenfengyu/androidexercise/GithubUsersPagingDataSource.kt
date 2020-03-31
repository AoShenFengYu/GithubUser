package com.aoshenfengyu.androidexercise

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class GithubUsersPagingDataSource() : PageKeyedDataSource<Int, GithubUser>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GithubUser>
    ) {
        val githubUsers = GithubRepository.instance.loadSGithubUsers(0)
        if (githubUsers != null && githubUsers.isNotEmpty()) {
            val nextOffset = githubUsers[githubUsers.size - 1].id + 1
            callback.onResult(githubUsers, null, nextOffset)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
        val offset = params.key
        val githubUsers = GithubRepository.instance.loadSGithubUsers(offset)
        if (githubUsers != null && githubUsers.isNotEmpty()) {
            val nextOffset = githubUsers[githubUsers.size - 1].id + 1
            callback.onResult(githubUsers, nextOffset)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GithubUser>) {
    }

    class Factory() : DataSource.Factory<Int, GithubUser>() {
        override fun create(): DataSource<Int, GithubUser> {
            return GithubUsersPagingDataSource()
        }
    }
}