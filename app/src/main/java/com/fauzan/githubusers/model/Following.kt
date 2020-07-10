package com.fauzan.githubusers.model

import com.google.gson.annotations.SerializedName

data class Following(
    var login: String?,

    @SerializedName("avatar_url")
    var avatar: String?
)