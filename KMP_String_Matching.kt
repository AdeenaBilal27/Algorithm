class KMP_String_Matching {
    fun KMPSearch(pat: String, txt: String) {
        val M = pat.length
        val N = txt.length

        val lps = IntArray(M)
        var j = 0 // index for pat[]

        computeLPSArray(pat, M, lps)
        var i = 0 // index for txt[]
        while (i < N) {
            if (pat[j] == txt[i]) {
                j++
                i++
            }
            if (j == M) {
                println(
                    "Found pattern "
                            + "at index " + (i - j)
                )
                j = lps[j - 1]
            } else if (i < N && pat[j] != txt[i]) {
                if (j != 0) j = lps[j - 1] else i = i + 1
            }
        }
    }

    fun computeLPSArray(
        pat: String,
        M: Int,
        lps: IntArray
    ) {
        var len = 0
        var i = 1
        lps[0] = 0
        while (i < M) {
            if (pat[i] == pat[len]) {
                len++
                lps[i] = len
                i++
            } else
            {
                if (len != 0) {
                    len = lps[len - 1]

                }
                else
                {
                    lps[i] = len
                    i++
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            val txt = "ababbaabaabcababaabbacaab"
            val pat = "aabaabcab"
            KMP_String_Matching().KMPSearch(pat, txt)
        }
    }
}