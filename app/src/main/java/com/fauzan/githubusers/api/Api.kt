package com.fauzan.githubusers.api

import com.fauzan.githubusers.BuildConfig
import com.fauzan.githubusers.model.DetailUser
import com.fauzan.githubusers.model.Followers
import com.fauzan.githubusers.model.Following
import com.fauzan.githubusers.model.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getUser(@Query("q") login: String): Call<ItemResponse>

    @GET("users/{user}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getDetailUser(@Path("user") user: String?): Call<DetailUser>

    @GET("users/{user}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowers(@Path("user") followers: String?): Call<ArrayList<Followers>>

    @GET("users/{user}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    fun getFollowing(@Path("user") followers: String?): Call<ArrayList<Following>>
}