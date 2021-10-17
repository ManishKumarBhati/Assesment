package com.manishbhati.data.data

import com.squareup.moshi.Json

data class DataResponse(
    @JvmField @Json(name = "results") val results: List<ResultsItem>
)

data class ResultsItem(
    @JvmField @Json(name = "gender") val gender: String,
    @JvmField @Json(name = "dob") val dob: Dob,
    @JvmField @Json(name = "name") val name: Name,
    @JvmField @Json(name = "location") val location: Location,
    @JvmField @Json(name = "cell") val cell: String,
    @JvmField @Json(name = "email") val email: String,
    @JvmField @Json(name = "picture") val picture: Picture
)

data class Dob(
    @JvmField @Json(name = "date") val date: String,
    @JvmField @Json(name = "age") val age: Int
)

data class Name(
    @JvmField @Json(name = "last") val last: String,
    @JvmField @Json(name = "title") val title: String,
    @JvmField @Json(name = "first") val first: String
)

data class Location(
    @JvmField @Json(name = "city") val city: String,
    @JvmField @Json(name = "street") val street: Street,
    @JvmField @Json(name = "postcode") val postcode: String
)

data class Picture(
    @JvmField @Json(name = "large") val large: String
)

data class Street(
    @JvmField @Json(name = "number") val number: Int,
    @JvmField @Json(name = "name") val name: String
)
