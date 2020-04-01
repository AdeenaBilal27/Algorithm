object RabinKarp {
    const val d = 10
    fun rabinKarp(pat: String, txt: String, q: Int) {
        val m = pat.length
        val n = txt.length
        var i: Int
        var j: Int
        var p = 0
        var t = 0
        var h = 1
        i = 0
        while (i < m - 1) {
            h = h * d % q
            i++
        }
        i = 0
        while (i < m) {
            p = (d * p + pat[i].toInt()) % q
            t = (d * t + txt[i].toInt()) % q
            i++
        }
        i = 0
        while (i <= n - m) {
            if (p == t) {
                j = 0
                while (j < m) {
                    if (txt[i + j] != pat[j]) break
                    j++
                }
                if (j == m) println("Pattern found at index " + (i + 1))
            }
            if (i < n - m) {
                t = (d * (t - txt[i].toInt() * h) + txt[i + m].toInt()) % q
                if (t < 0) t = t + q
            }
            i++
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val txt = "ABCCDDAEFG"
        val pat = "CDD"
        val q = 13
        rabinKarp(pat, txt, q)
    }
}