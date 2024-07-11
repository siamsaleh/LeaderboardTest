package com.example.leaderboardtest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.leaderboardtest.R
import com.example.leaderboardtest.adapter.LeaderboardAdapter
import com.example.leaderboardtest.databinding.ActivityMainBinding
import com.example.leaderboardtest.model.HostDailyData
import com.example.leaderboardtest.utils.CommonUtils
import com.example.leaderboardtest.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var leaderboardList: HostDailyData
    private lateinit var leaderboardAdapter: LeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setContentView(binding.root)

        loadLiveData()
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.getLeaderboard().observe(this){ leaderboardResponse ->

            leaderboardList = leaderboardResponse.data

            setTopThree()

            leaderboardAdapter = LeaderboardAdapter(this, CommonUtils().initGlide(this))
            leaderboardAdapter.updateLeaderboardList(leaderboardList)

            binding.leaderboardRecyclerview.adapter = leaderboardAdapter
            //Log.d("API_RESPONSE", "observeLiveData: $leaderboardList")
        }
    }

    private fun setTopThree() {

        val top3 = leaderboardList.hostDaily.top3

        val requestManager = CommonUtils().initGlide(this)

        // first
        requestManager
            .load(top3[0].profilePic)
            .into(binding.profile.topProfileImage)
        binding.profileName1.text = "${top3[0].firstName} ${top3[0].lastName}"

        // second
        requestManager
            .load(top3[1].profilePic)
            .into(binding.profile2.topProfileImage)
        binding.profile2.topFrame.setImageDrawable(getResources().getDrawable(R.drawable.ld_top_02));
        binding.profileName2.text = "${top3[1].firstName} ${top3[1].lastName}"

        // third
        requestManager
            .load(top3[2].profilePic)
            .into(binding.profile3.topProfileImage)
        binding.profile3.topFrame.setImageDrawable(getResources().getDrawable(R.drawable.ld_top_03));
        binding.profileName3.text = "${top3[2].firstName} ${top3[2].lastName}"
    }

    private fun loadLiveData() {
        viewModel.loadLeaderboard()
    }

}