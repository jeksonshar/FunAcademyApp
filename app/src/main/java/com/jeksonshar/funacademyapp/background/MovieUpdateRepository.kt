package com.jeksonshar.funacademyapp.background

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import java.util.concurrent.TimeUnit

class MovieUpdateRepository {

    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
//        .setRequiresCharging(true)
        .build()
    val movieUpdateWorker = PeriodicWorkRequestBuilder<MovieUpdateWorker>(8, TimeUnit.HOURS)
        .setConstraints(constraint)
        .build()
}