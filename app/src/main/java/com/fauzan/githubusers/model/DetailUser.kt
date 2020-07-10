package com.fauzan.githubusers.model

import com.google.gson.annotations.SerializedName

data class DetailUser(
    var login: String?,
    @SerializedName("avatar_url")
    var avatar: String?,

    var name: String?,
    var company: String?,
    var location: String?,

    @SerializedName("public_repos")
    var repo: Int?,

    var followers: Int?,
    var following: Int?
)