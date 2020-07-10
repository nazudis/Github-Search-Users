package com.fauzan.githubusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fauzan.githubusers.model.ItemResponse
import com.fauzan.githubusers.model.UserList
import com.fauzan.githubusers.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<UserList>>()

    fun setSearchUser(name: String) {
        val list = ArrayList<UserList>()

        RetrofitClient.instance.getUser(name).enqueue(object : Callback<ItemResponse> {
            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d("onFailure", t.message.toString())
            }

            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                //Start Parsing JSON
                try {
                    val listResponse = response.body()?.items
                    listResponse?.let { list.addAll(it) }

                    listUser.postValue(listResponse)

                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

        })
    }

    fun getSearchResult(): LiveData<ArrayList<UserList>> {
        return listUser
    }
}