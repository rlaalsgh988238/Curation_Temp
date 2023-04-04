package com.example.curation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.curation.Data.Data
import com.example.curation.RecyclerView.FlowerRecyclerAdapter
import com.example.curation.databinding.ActivityMainBinding
import com.example.curation.login.LoginActivity
import com.example.curation.utils.Constants.TAG
import com.google.android.material.snackbar.Snackbar

// release

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var adapter: FlowerRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        데이터 갱신
        Log.d(TAG, "MainActivity - Data 업데이트 전")
        adapter = FlowerRecyclerAdapter(this)
        Data.dataUpdate(this, adapter)
        Log.d(TAG, "MainActivity - Data 업데이트 후")


        binding.tempBtn.setOnClickListener {
            Data.dataSort()
            adapter.notifyDataSetChanged()
        }

//        리사이클러 뷰 생성
        makeRecyclerView()

    }

    // 메인 택티비티에서 뒤로가기 방지, 터치 두번으로 앱 종료
    var mBackWait:Long = 0
    override fun onBackPressed() {
        // 뒤로가기 버튼 클릭
        if(System.currentTimeMillis() - mBackWait >=2000 ) {
            mBackWait = System.currentTimeMillis()
            Toast.makeText(this, "이전버튼을 누르면 앱이 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.finishAffinity(this)
            System.runFinalization()
            System.exit(0)
        }
    }

//    리사이클러 뷰 생성
    private fun makeRecyclerView() {
        binding.flowerRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.flowerRecyclerView.adapter = adapter
    }

//    -> 정렬순서를 정할 수 있는 화면 필요할 것 같습니다!
}
