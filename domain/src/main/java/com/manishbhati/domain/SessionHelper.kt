package com.manishbhati.domain

interface SessionHelper {
    fun isRefreshRequired(): Boolean
    fun saveToken()
}