package com.manishbhati.data.mapper

import com.manishbhati.data.data.DataResponse

import com.manishbhati.data.doa.DoaService
import com.manishbhati.data.doa.TableData
import com.manishbhati.domain.Response
import com.manishbhati.domain.Result
import java.util.*
import retrofit2.Response as ApiResponse

fun ApiResponse<DataResponse>.mapper(doaService: DoaService): Result<List<Response>> {
    try {
        val response = this
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                val data = body.transform()
                doaService.insertAll(
                    data.map {
                        TableData(
                            id = it.id,
                            gender = it.gender,
                            dob = it.age,
                            name = it.name,
                            mob = it.mob,
                            location = it.location,
                            img = it.img,
                            email = it.email
                        )
                    }
                )
                return Result.Success(data)
            }
        }
        return Result.Failure(Exception(" ${response.code()} ${response.message()}"))
    } catch (e: Exception) {
        return Result.Failure(Exception((e.message ?: e.toString())))
    }
}


fun DataResponse.transform(): List<Response> {
    return results.map {
        Response(
            id = Date().time,
            gender = it.gender,
            age = it.dob.age,
            name = "${it.name.title} ${it.name.first} ${it.name.last}",
            mob = it.cell,
            location = "${it.location.street} ${it.location.city} ${it.location.postcode}",
            img = it.picture.large,
            email = it.email
        )
    }

}