package com.aoshenfengyu.androidexercise


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    fun fetchGithubUsers(@Query("since") since: Int): Call<List<GithubUser>>

    @GET("users/{user_name}")
    fun fetchGithubUser(@Path("user_name") userName: String): Call<GithubUser>

}
