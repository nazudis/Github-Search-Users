package com.fauzan.githubusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fauzan.githubusers.model.Followers
import com.fauzan.githubusers.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<Followers>>()

    fun setFollowers(name: String?) {
        val list = ArrayList<Followers>()

        RetrofitClient.instance.getFollowers(name).enqueue(object : Callback<ArrayList<Followers>> {
            override fun onFailure(call: Call<ArrayList<Followers>>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Followers>>,
                response: Response<ArrayList<Followers>>
            ) {
                //Start Parsing JSON
                try {
                    val listResponse = response.body()
                    listResponse?.let { list.addAll(it) }

                    listFollowers.postValue(listResponse)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

        })
    }

    fun getFollowers(): LiveData<ArrayList<Followers>> {
        return listFollowers
    }
}