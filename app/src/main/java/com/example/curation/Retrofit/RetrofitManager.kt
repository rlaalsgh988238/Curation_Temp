package com.example.curation.Retrofit

import android.util.Log
import com.example.curation.Data.ApiData
import com.example.curation.utils.API
import com.example.curation.utils.Constants.TAG
import com.example.curation.utils.RESPONSE_STATE
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.net.URL

class RetrofitManager {
    companion object {
        val instance = RetrofitManager()
    }

    private val iRetrofit: IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    fun showFlowerList(completion : (RESPONSE_STATE, String)->Unit) {

        val call = iRetrofit?.showFlowerList().let {
            it
        }?: return

        call.enqueue(object: retrofit2.Callback<JsonElement>{
//            응답에 성공한 경우
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "RetrofitManager - call.enqueue 응답 성공 / response: ${response.body().toString()}")

                completion(RESPONSE_STATE.OKAY, response.body().toString())
            }

//            응답에 실패한 경우
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "RetrofitManager - call.enqueue 응답 실패 / t: $t")

                completion(RESPONSE_STATE.FAIL, t.toString())
            }

        })
    }
}