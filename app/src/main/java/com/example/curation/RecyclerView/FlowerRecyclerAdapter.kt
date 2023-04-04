package com.example.curation.RecyclerView

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.curation.Data.Data
import com.example.curation.R
import com.example.curation.databinding.FlowerRecyclerViewItemBinding
import com.example.curation.detailPage.DetailActivity
import com.example.curation.utils.Constants.TAG

class FlowerRecyclerAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        FlowerViewHolder(FlowerRecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as FlowerViewHolder)

//        각 위치에 데이터를 놓는다.
        binding.apply {
            Glide.with(context)
                .load(Data.flowerData[position].profileImage) // 불러올 이미지 url
                .placeholder(R.drawable.ic_launcher_background) // 이미지 로딩 시작하기 전 표시할 이미지
                .error(R.drawable.ic_launcher_background) // 로딩 에러 발생 시 표시할 이미지
                .fallback(R.drawable.ic_launcher_background) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지 +) .circleCrop() // 동그랗게 자르기
                .into(holder.image) // 이미지를 넣을 뷰
        }
        binding.bind(Data.flowerData[position])

        binding.container.setOnClickListener {
            Log.d(TAG, "FlowerRecyclerAdapter - container.setOnClickListener() 시작")
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("index", Data.flowerData[position].id)
            Log.d(TAG, "FlowerRecyclerAdapter - container.setOnClickListener() intent 값 전달")
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int  = Data.flowerData.size

}