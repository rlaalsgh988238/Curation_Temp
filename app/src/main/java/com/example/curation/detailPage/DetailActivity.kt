package com.example.curation.detailPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.curation.Data.Data
import com.example.curation.R
import com.example.curation.databinding.ActivityDetailBinding
import com.example.curation.utils.Constants.TAG

class DetailActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        // create adpater
        val adapter = StoryFragmentAdapter(this)

        Log.d(TAG, "DetailActivity - onCreate() 실행")
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        해당 하는 데이터의 id 값 전달
        Log.d(TAG, "DetailActivity - id 값 전달")
        val index = intent.getIntExtra("index", 0)

//        id값을 통해 Data에서 값을 찾아 따로 저장
        Log.d(TAG, "DetailActivity - id 값을 통해 data 찾기")
        val data = Data.dataSearch(index)

        // set view's contents
        binding.detailName.setText(data.name)
        binding.detailLanguage.setText(data.language)

        Log.d(TAG, "DetailActivity - 이미지 생성")
        Glide.with(this)
            .load(data.profileImage) // 불러올 이미지 url
            .placeholder(R.drawable.ic_launcher_background) // 이미지 로딩 시작하기 전 표시할 이미지
            .error(R.drawable.ic_launcher_background) // 로딩 에러 발생 시 표시할 이미지
            .fallback(R.drawable.ic_launcher_background) // 로드할 url 이 비어있을(null 등) 경우 표시할 이미지 +) .circleCrop() // 동그랗게 자르기
            .into(binding.detailProfileImage) // 이미지를 넣을 뷰



        // create fragment
        // and add fragment to adpater
        val story = StoryFragment(index) // this reference is temporary, have to be changed to story index
        adapter.addFragment(story)

        // insert fragment to view
        binding.StoryFragment.adapter = adapter
    }

}
