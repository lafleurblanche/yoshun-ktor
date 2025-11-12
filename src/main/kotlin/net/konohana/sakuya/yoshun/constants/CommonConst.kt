package net.konohana.sakuya.yoshun.constants

class CommonConst {
    companion object {
        // staNameを分割する際の長さ（staName1の最大長、substringのステップ）
        const val SPLIT_LENGTH = 2

        // 分割処理を行うStaNameの最小長さ（staName1とstaName2に分ける条件）
        const val MIN_LENGTH_FOR_SPLIT = 4
    }
}
