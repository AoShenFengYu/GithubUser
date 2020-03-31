package com.aoshenfengyu.androidexercise

import androidx.recyclerview.widget.DiffUtil

class GithubUser {

    /**
     * login : mojombo
     * id : 1
     * node_id : MDQ6VXNlcjE=
     * avatar_url : https://avatars0.githubusercontent.com/u/1?v=4
     * gravatar_id :
     * url : https://api.github.com/users/mojombo
     * html_url : https://github.com/mojombo
     * followers_url : https://api.github.com/users/mojombo/followers
     * following_url : https://api.github.com/users/mojombo/following{/other_user}
     * gists_url : https://api.github.com/users/mojombo/gists{/gist_id}
     * starred_url : https://api.github.com/users/mojombo/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/mojombo/subscriptions
     * organizations_url : https://api.github.com/users/mojombo/orgs
     * repos_url : https://api.github.com/users/mojombo/repos
     * events_url : https://api.github.com/users/mojombo/events{/privacy}
     * received_events_url : https://api.github.com/users/mojombo/received_events
     * type : User
     * site_admin : false
     */
    var login: String? = null
    var id = 0
    var node_id: String? = null
    var avatar_url: String? = null
    var gravatar_id: String? = null
    var url: String? = null
    var html_url: String? = null
    var followers_url: String? = null
    var following_url: String? = null
    var gists_url: String? = null
    var starred_url: String? = null
    var subscriptions_url: String? = null
    var organizations_url: String? = null
    var repos_url: String? = null
    var events_url: String? = null
    var received_events_url: String? = null
    var type: String? = null
    var isSite_admin = false
    var location: Any? = null
    var blog: String? = null

    object DiffUtils : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
            return oldItem.id == oldItem.id
        }
    }
}