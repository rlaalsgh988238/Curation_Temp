package com.example.curation.Retrofit

import android.util.Log
import com.example.curation.utils.Constants.TAG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofitClient: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {

        Log.d(TAG, "RetrofitClient.getClient() - 시작")

        if(retrofitClient == null) {

            Log.d(TAG, "RetrofitClient.getClient() - retrofitClient==null, 생성 중")
//            레트로핏 빌터 패턴은 디자인 패턴중 생성패턴이
//            레트로핏 빌더를 통해 인스턴스 생성
            retrofitClient = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        Log.d(TAG, "RetrofitClient.getClient() - retrofitClient 생성")

        return retrofitClient
    }
}