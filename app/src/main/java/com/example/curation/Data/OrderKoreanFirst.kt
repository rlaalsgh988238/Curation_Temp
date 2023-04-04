package com.example.curation.Data

object OrderKoreanFirst {
    private const val LEFT = -1
    private const val RIGHT = 1

//    양수 -> 오른쪽이 우선순위, 음수 -> 왼쪽이 우선순위, 0 -> 둘의 문자열이 동일
    fun compare(left: String, right: String) : Int {
        val _left = left.uppercase().filterNot(Char::isWhitespace)
        val _right = right.uppercase().filterNot(Char::isWhitespace)    //공백과 영어 대소문자의 영향을 받지 않기 위해서

        val (llen, rlen) = _left.length to right.length
        val mlen = llen.coerceAtMost(rlen)

        for (i in 0 until mlen) {
            val (lc, rc) = _left[i] to _right[i]        //각 자리의 문자를 비교하기 위해서

            if(lc != rc) {      // 1. 두 문자가 같지 않은 경우
                return  if(conditionKoreanAndEnglish(lc, rc)     // 2. 한글이 포함된 경우 또는 영어&숫자 조합인 경우
                        || conditionKoreanAndNumber(lc, rc)
                        || conditionEnglishAndNumber(lc, rc)
                        || conditionKoreanAndSpecial(lc, rc)
                )
                            -(lc-rc)
                        else if (conditionEnglishAndSpecial(lc, rc) || conditionNumberAndSpecial(lc, rc)){      // 2. 영&특, 수&특 의 조합인 경우
                            if(isEnglish(lc) || isNumber(lc)) LEFT
                            else RIGHT
                }       else {lc - rc}          // 2. 한글, 영어, 특수문자, 숫자 조합이 아닌경우
            }           // 1. 두 문자가 같은 경우 다음 문자를 비교한다.
        }
        return llen -rlen       // 0. mlen까지의 문자가 모두 같은 경우 길이가 더 짧은 문자열을 우선순위에 둔다.
    }

    private fun isEnglish(ch: Char) : Boolean = ch in 'A'..'Z'                                                                              //65~90                    ASCII
    private fun isKorean(ch: Char) : Boolean = ch in '가'..'힣' || ch in 'ㄱ'..'ㅣ'                                                     //U+AC00 - U+D7A3, ㄱ~ㅣ?    unicode
    private fun isNumber(ch: Char) : Boolean = ch in '0'..'9'                                                                               //48~57                     ASCII
    private fun isSpecial(ch: Char) : Boolean = ch in '!'..'/' || ch in ':'..'@' || ch in '['..'`' || ch in '{'..'~'      //33..126 광범위하게 널려 있음    ASCII

    private fun conditionKoreanAndEnglish(c1: Char, c2: Char) = isKorean(c1) && isEnglish(c2) || isEnglish(c1) && isKorean(c2)
    private fun conditionKoreanAndNumber(c1: Char, c2: Char) = isKorean(c1) && isNumber(c2) || isNumber(c1) && isKorean(c2)
    private fun conditionKoreanAndSpecial(c1: Char, c2: Char) = isKorean(c1) && isSpecial(c2) || isSpecial(c1) && isKorean(c2)
    private fun conditionEnglishAndNumber(c1: Char, c2: Char) = isEnglish(c1) && isNumber(c2) || isNumber(c1) && isEnglish(c2)
    private fun conditionEnglishAndSpecial(c1: Char, c2: Char) = isEnglish(c1) && isSpecial(c2) || isSpecial(c1) && isEnglish(c2)
    private fun conditionNumberAndSpecial(c1: Char, c2: Char) = isNumber(c1) && isSpecial(c2) || isSpecial(c1) && isNumber(c2)
}