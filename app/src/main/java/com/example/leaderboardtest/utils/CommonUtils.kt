package com.example.leaderboardtest.utils

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.leaderboardtest.R

class CommonUtils {

    fun initGlide(context: Context): RequestManager {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.ic_profile)
            .error(R.drawable.ic_profile)
        return Glide.with(context)
            .setDefaultRequestOptions(options)
    }
}