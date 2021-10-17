package com.manishbhati.data.network

import com.manishbhati.data.BuildConfig
import com.manishbhati.data.util.BASE_URL
import com.manishbhati.data.util.SHA
import okhttp3.CertificatePinner
import java.net.URL

object SSLCertificatePinnerImpl {
    fun getPinner(): CertificatePinner {
        if (BuildConfig.DEBUG) {
            return CertificatePinner.DEFAULT
        }
        return CertificatePinner.Builder()
            .add(URL(BASE_URL).host)
            .add(SHA)
            .build()
    }
}