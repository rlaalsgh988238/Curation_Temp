package com.example.curation.Retrofit

import com.example.curation.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {

//    처음 화면에 필요한 꽃 리스트 데이터
    @GET(API.FLOWER_LIST)

    fun showFlowerList() : Call<JsonElement>
}