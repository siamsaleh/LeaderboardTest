package com.example.leaderboardtest.restapi

import com.example.leaderboardtest.helper.ApiFetchListener
import com.example.leaderboardtest.model.LeaderboardResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiHelper {
    companion object {

        fun getLeaderboard(
            apiFetchListener: ApiFetchListener<LeaderboardResponse>
        ) {
            val apiService = ApiClient.getInstance().create(ApiService::class.java)

            apiService.getLeaderboard()?.enqueue(object : Callback<LeaderboardResponse?> {
                override fun onResponse(
                    call: Call<LeaderboardResponse?>,
                    response: Response<LeaderboardResponse?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { apiFetchListener.onSuccess(it) }
                    } else {
                        apiFetchListener.onError(response.message(), response.code())
                    }
                }

                override fun onFailure(call: Call<LeaderboardResponse?>, t: Throwable) {
                    t.message?.let { apiFetchListener.onError(it, 0) }
                }
            })
        }
    }
}