package com.example.curation.Data

import android.content.Context
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import com.example.curation.RecyclerView.FlowerRecyclerAdapter
import com.example.curation.Retrofit.RetrofitManager
import com.example.curation.utils.Constants.TAG
import com.example.curation.utils.RESPONSE_STATE
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject

object Data {
    var flowerData = ArrayList<FlowerModel>()

//    꽃 데이터 갱신 -> 꽃 데이터 다시 받아오기
    fun dataUpdate(context: Context, adapter: FlowerRecyclerAdapter) {

    Log.d(TAG, "Data - 서버로 받은 데이터 받기 전")

//        서버에서 받은 데이터로 갱신
        RetrofitManager.instance.showFlowerList(completion = {
            responseState, responseBody->
            when(responseState) {
                RESPONSE_STATE.OKAY -> {
                    Log.d(TAG, "Data.dataUpdata() - api 호출 성공 / $responseBody")


//                    retrofit 으로 받은 데이터 responseBody(reponse<JsonElement>)를 .body().toString()으로 변환 한 것을 넘겨 받은 것
                    val data = JSONObject(responseBody).getJSONArray("data")
                    val gson = Gson()
                    for (i in 0 until data.length()){
                        val temp = data[i].toString()
                        val data = gson.fromJson(temp, ReceiveData::class.java)
                        flowerData.add(FlowerModel(data.flower_id, data.flower_name, data.flower_profile_img_url, data.flower_floriography, data.flower_story, data.flower_pictures_url))
                    }

                    //


                    //
//                    dataSort()를 위해서 임의로 추가함
                    flowerData.shuffle()

                    Log.d(TAG, "flowerData : ${flowerData.count()} 안의 데이터 : $flowerData \n data : ${data.length()}")
                }

                RESPONSE_STATE.FAIL -> {
                    Toast.makeText(context, "api 호출 에러입니다", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "Data.dataUpdate() - api 호출 실패 / $responseBody")
                }
/*                else -> {

                }   // -> 왜 이게 필요 없는 거지?
*/
            }
//            adapter에 데이터 변환 알리기
            adapter.notifyDataSetChanged()
        })
    Log.d(TAG, "Data - 서버로 받은 데이터 받기 후")
    }
//    꽃 데이터 정렬 -> 꽃의 이름 순으로 정렬함
    fun dataSort() {
//        flowerData.sortedWith(Comparator<>(OrderKoreanFirst::compare)) -> 정렬된 새로운 객체 생성
//        flowerData.sortWith(Comparator(OrderKoreanFirst::compare))     -> 기존의 객체를 정렬

        flowerData.sortWith {       //-> 기존의 객체를 정렬
            o1, o2 -> OrderKoreanFirst.compare(o1.name.toString(), o2.name.toString())
        }


    }
//    꽃 데이터 탐색 -> detail page를 들어갈 때 꽃의 id를 통해서 전달해 줌

    fun dataSearch(id: Int): FlowerModel {
        var dcount = flowerData.count()

        Log.d(TAG, "Data - dataSearch() 시작")

        for (i in 0 until dcount){
            if(flowerData[i].id == id){
                Log.d(TAG, "Data - dataSearch() 데이터 찾음")
                return flowerData[i]
            }
        }

//      아무 데이터도 못 찾은 경우
        Log.d(TAG, "Data - dataSearch() 데이터 못 찾음")
       return flowerData[0]
    }
}

