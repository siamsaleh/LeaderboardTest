package com.example.leaderboardtest.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leaderboardtest.helper.ApiFetchListener
import com.example.leaderboardtest.model.LeaderboardResponse
import com.example.leaderboardtest.restapi.ApiHelper

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val leaderboardLiveData = MutableLiveData<LeaderboardResponse>()
    private val statusMessageLiveData = MutableLiveData<String>()

    fun getLeaderboard(): LiveData<LeaderboardResponse> {
        return leaderboardLiveData
    }

    fun getStatusMessage(): LiveData<String> {
        return statusMessageLiveData
    }

    fun loadLeaderboard() {

        ApiHelper.getLeaderboard(
            object : ApiFetchListener<LeaderboardResponse> {
                override fun onSuccess(responseData: LeaderboardResponse) {
                    leaderboardLiveData.postValue(responseData)
                }

                override fun onError(errorMessage: String, responseCode: Int) {
                    statusMessageLiveData.postValue(errorMessage)
                }
            })
    }
}