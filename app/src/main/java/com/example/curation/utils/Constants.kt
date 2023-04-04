package com.example.curation.utils

object Constants {
    const val TAG: String = "로그"
}

enum class RESPONSE_STATE {
    OKAY,
    FAIL
}

object API {
    const val BASE_URL: String = "http://3.35.133.75"

    const val FLOWER_LIST: String = "/flowers"
//    const val FLOWER_LIST: String = "/flowers/" 이것도 되는지 체크 바람
}