package com.fauzan.githubusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fauzan.githubusers.model.Following
import com.fauzan.githubusers.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    val listFolloing = MutableLiveData<ArrayList<Following>>()

    fun setFollowing(name: String?) {
        val list = ArrayList<Following>()

        RetrofitClient.instance.getFollowing(name).enqueue(object : Callback<ArrayList<Following>> {
            override fun onFailure(call: Call<ArrayList<Following>>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArrayList<Following>>,
                response: Response<ArrayList<Following>>
            ) {
                //Start Parsing JSON
                try {
                    val listResponse = response.body()
                    listResponse?.let { list.addAll(it) }

                    listFolloing.postValue(listResponse)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

        })
    }

    fun getFollowing(): LiveData<ArrayList<Following>> {
        return listFolloing
    }
}