package com.manishbhati.domain


interface Repository {
    suspend fun getData(): Result<List<Response>>
    suspend fun update(id: Long, status: Boolean)
}
