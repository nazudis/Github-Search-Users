package com.fauzan.githubusers.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fauzan.githubusers.model.DetailUser
import com.fauzan.githubusers.service.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailUserViewModel : ViewModel() {

    val dataUser = MutableLiveData<DetailUser>()

    fun setDataUser(name: String?) {

        RetrofitClient.instance.getDetailUser(name).enqueue(object : Callback<DetailUser> {
            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                Log.d("onFailure..", t.message.toString())
            }

            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                val detailUser = response.body()

                try {
                    if (detailUser != null) {
                        dataUser.postValue(detailUser)
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

        })
    }

    fun getDataUser(): LiveData<DetailUser> {
        return dataUser
    }
}