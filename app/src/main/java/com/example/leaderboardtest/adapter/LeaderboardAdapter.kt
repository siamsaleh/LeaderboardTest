package com.example.leaderboardtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.leaderboardtest.R
import com.example.leaderboardtest.model.HostDailyData
import com.example.leaderboardtest.model.User

class LeaderboardAdapter(
    private val context: Context,
    private val requestManager: RequestManager
): RecyclerView.Adapter<LeaderboardAdapter.LeaderboardViewHolder>() {

        private lateinit var leaderboardList: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_leaderloard, parent, false)
        return LeaderboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return leaderboardList.size
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        leaderboardOnBind(holder, leaderboardList[position])
    }

    private fun leaderboardOnBind(holder: LeaderboardViewHolder, user: User) {

        holder.profileName.text = "${user.firstName} ${user.lastName}"
        holder.serialNo.text = "${user.position}"

        if (user.gender == ""){
            holder.genderTxt.visibility = View.GONE
        }else{
            holder.genderTxt.visibility = View.VISIBLE
            holder.genderTxt.text = "${user.gender}"
        }

        holder.levelLxt.text = "${user.level}"
        holder.giftCoin.text = "${formatToK(user.giftCoin)}"

        requestManager
            .load(user.profilePic)
            .into(holder.profileImage)
    }

    fun updateLeaderboardList(hostDailyData: HostDailyData) {
        leaderboardList = hostDailyData.hostDaily.all
    }

    private fun formatToK(value: Int): String {
        return if (value >= 1000) {
            "%.1fk".format(value / 1000.0)
        } else {
            value.toString()
        }
    }

    class LeaderboardViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val profileImage: ImageView = itemView.findViewById(R.id.profile_image)
        val profileName: TextView = itemView.findViewById(R.id.profile_name)
        val serialNo: TextView = itemView.findViewById(R.id.serial_no)
        val giftCoin: TextView = itemView.findViewById(R.id.gift_coin)
        val genderTxt: TextView = itemView.findViewById(R.id.gender_txt)
        val levelLxt: TextView = itemView.findViewById(R.id.level_txt)
    }
}