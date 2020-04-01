import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class BoyerMoore {
    fun findPattern(t: String, p: String) {
        val text = t.toCharArray()
        val pattern = p.toCharArray()
        val pos = indexOf(text, pattern)
        if (pos == -1) println("\nNo Match\n") else println("Pattern found at position : $pos")
    }

    fun indexOf(text: CharArray, pattern: CharArray): Int
    {
        if (pattern.size == 0) return 0
        val charTable = makeCharTable(pattern)
        val offsetTable = makeOffsetTable(pattern)
        var i = pattern.size - 1
        var j: Int
        while (i < text.size) {
            j = pattern.size - 1
            while (pattern[j] == text[i]) {
                if (j == 0) return i
                --i
                --j
            }
            // i += pattern.length - j; // For naive method
            i += Math.max(offsetTable[pattern.size - 1 - j], charTable[text[i].toInt()])
        }
        return -1
    }

    private fun makeCharTable(pattern: CharArray): IntArray {
        val ALPHABET_SIZE = 256
        val table = IntArray(ALPHABET_SIZE)
        for (i in table.indices) table[i] = pattern.size
        for (i in 0 until pattern.size - 1) table[pattern[i].toInt()] = pattern.size - 1 - i
        return table
    }

    companion object {
        private fun makeOffsetTable(pattern: CharArray): IntArray {
            val table = IntArray(pattern.size)
            var lastPrefixPosition = pattern.size
            for (i in pattern.indices.reversed()) {
                if (isPrefix(pattern, i + 1)) lastPrefixPosition = i + 1
                table[pattern.size - 1 - i] = lastPrefixPosition - i + pattern.size - 1
            }
            for (i in 0 until pattern.size - 1) {
                val slen = suffixLength(pattern, i)
                table[slen] = pattern.size - 1 - i + slen
            }
            return table
        }

        private fun isPrefix(pattern: CharArray, p: Int): Boolean {
            var i = p
            var j = 0
            while (i < pattern.size) {
                if (pattern[i] != pattern[j]) return false
                ++i
                ++j
            }
            return true
        }

        private fun suffixLength(pattern: CharArray, p: Int): Int {
            var len = 0
            var i = p
            var j = pattern.size - 1
            while (i >= 0 && pattern[i] == pattern[j]) {
                len += 1
                --i
                --j
            }
            return len
        }

        @Throws(IOException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val br = BufferedReader(InputStreamReader(System.`in`))
            println("Boyer Moore Algorithm Test\n")
            println("\nEnter Text\n")
            val text = br.readLine()
            println("\nEnter Pattern\n")
            val pattern = br.readLine()
            val bm = BoyerMoore()
            bm.findPattern(text, pattern)
        }
    }
}