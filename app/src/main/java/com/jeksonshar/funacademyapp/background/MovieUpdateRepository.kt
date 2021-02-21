package com.jeksonshar.funacademyapp.background

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit

class MovieUpdateRepository {

    private val constraint = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()
    val movieUpdateWorker = PeriodicWorkRequest.Builder(
        MovieUpdateWorker::class.java,
        8,
        TimeUnit.HOURS,
        4,
        TimeUnit.HOURS
    )
        .setConstraints(constraint)
        .build()
}