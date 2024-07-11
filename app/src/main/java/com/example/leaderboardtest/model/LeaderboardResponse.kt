package com.example.leaderboardtest.model

import com.google.gson.annotations.SerializedName

data class LeaderboardResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: HostDailyData
)

data class HostDailyData(
    @SerializedName("host_daily") val hostDaily: HostDaily
)

data class HostDaily(
    @SerializedName("top3") val top3: List<User>,
    @SerializedName("all") val all: List<User>
)

data class User(
    @SerializedName("userid") val userId: String,
    @SerializedName("hive_id") val hiveId: String,
    @SerializedName("username") val username: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("profile_pic") val profilePic: String,
    @SerializedName("user_tag") val userTag: String,
    @SerializedName("level") val level: Int,
    @SerializedName("gender") val gender: String,
    @SerializedName("giftcoin") val giftCoin: Int,
    @SerializedName("position") val position: Int
)
