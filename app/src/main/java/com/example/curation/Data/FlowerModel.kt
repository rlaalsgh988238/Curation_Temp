package com.example.curation.Data

import java.sql.ClientInfoStatus
import java.time.LocalDateTime

data class ApiData(
    val message: String,
    val data: FlowerModel,
    val timestamp: LocalDateTime,
    val status: Int
)
data class FlowerModel(
    var id: Int? = 0,
    var name: String? = "",
    var profileImage: String? = "",
    var language: String? = "",
    var content: String? = "",
    var stickImage: ArrayList<String>
)

data class ReceiveData(
    var flower_id: Int,
    var flower_name: String,
    var flower_profile_img_url: String,
    var flower_floriography: String,
    var flower_story: String,
    var flower_pictures_url: ArrayList<String>
)

//Data.search() 또한 이 flower_id로 찾을 예정