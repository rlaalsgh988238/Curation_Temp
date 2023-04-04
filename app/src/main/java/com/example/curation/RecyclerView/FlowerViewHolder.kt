package com.example.curation.RecyclerView

import androidx.recyclerview.widget.RecyclerView
import com.example.curation.Data.FlowerModel
import com.example.curation.databinding.FlowerRecyclerViewItemBinding

class FlowerViewHolder(itemView: FlowerRecyclerViewItemBinding): RecyclerView.ViewHolder(itemView.root) {
    val container = itemView.flowerContainer
    val image = itemView.flowerImg
    private val name = itemView.flowerNameTxt
    private val language = itemView.flowerLanguageTxt
    private val story = itemView.flowerStoryTxt

//    기본 생성자
    init {

    }

//
    fun bind(myModel: FlowerModel){
        name.text = "이름 : " + myModel.name
        language.text = "꽃말 : " + myModel.language
        story.text = myModel.content
/* -> context 때문에 Glide를 여기에서 사용안하고 RecyclerViewAdapter에서 사용한다.
    Glide.with(context)
        .load(myModel.profileImage) // 불러올 이미지 url
        .placeholder(R.drawable.ic_launcher_background) // 이미지 로딩 시작하기 전 표시할 이미지
        .error(R.drawable.ic_launcher_background) // 로딩 에러 발생 시 표시할 이미지
        .fallback(R.drawable.ic_launcher_background) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지
        .circleCrop() // 동그랗게 자르기
        .into(image) // 이미지를 넣을 뷰
*/
    }
}
