package com.fauzan.githubusers.model

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("avatar_url")
    var avatar: String?,
    var login: String?
)