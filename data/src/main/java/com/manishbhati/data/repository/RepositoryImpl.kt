package com.manishbhati.data.repository

import android.content.SharedPreferences
import com.manishbhati.data.ApiService
import com.manishbhati.data.doa.DoaService
import com.manishbhati.data.mapper.mapper
import com.manishbhati.data.network.SessionManager
import com.manishbhati.domain.Repository
import com.manishbhati.domain.Response
import com.manishbhati.domain.Result
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    val api: ApiService,
    val doaService: DoaService,
    val sessionManager: SessionManager,
    val sharedPreferences: SharedPreferences
) :
    Repository {
    override suspend fun getData(): Result<List<Response>> {
        return api.getData().mapper(doaService)
    }

    override suspend fun update(id: Long, status: Boolean) {
        doaService.update(id, status)
    }
}