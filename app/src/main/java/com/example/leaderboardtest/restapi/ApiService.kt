package com.example.leaderboardtest.restapi

import com.example.leaderboardtest.model.LeaderboardResponse
import retrofit2.Call
import retrofit2.http.GET

interface  ApiService {

    @GET("mhasancse17/JsonFile/main/leaderboard.json")
    fun getLeaderboard(): Call<LeaderboardResponse?>?
}