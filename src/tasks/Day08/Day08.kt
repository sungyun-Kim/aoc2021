fun main() {

    fun hasHash(input: List<String>, digit: String): Boolean {

        var result = false
        val tempSum = digit.sumOf { it.toInt() }
        val numList = mutableListOf(2, 3, 4, 7)

        for (i in input) {
            if (i.length == digit.length) {
                val signalSum = i.sumOf { it.toInt() }
                if (signalSum == tempSum) {
                    for (j in numList) {
                        if (digit.length == j) result = true
                    }
                }
            }
        }
        return result
    }

    fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el): List<List<T>> {
        return if (sub.isEmpty()) listOf(fin)
        else sub.flatMap { permutation(el, fin + it, sub - it) }
    }

    fun part1(input: List<String>): Int {
        var count = 0

        for (i in input) {

            val tempList = i.split("|")

            val signals = tempList.first().split(" ").filter { it.isNotBlank() }.map { it.replace(" ", "") }
            val digits = tempList.last().split(" ").filter { it.isNotBlank() }.map { it.replace(" ", "") }

            for (j in digits) {
                if (hasHash(signals, j)) count++
            }

        }

        return count
    }

    fun part2(input: List<String>): Int {
        var finalResult = 0

        val segmentDigits = mutableListOf("a", "b", "c", "d", "e", "f", "g")
        val digitsList = mutableListOf(
                mutableListOf(0, 1, 2, 4, 5, 6),
                mutableListOf(2, 5),
                mutableListOf(0, 2, 3, 4, 6),
                mutableListOf(0, 2, 3, 5, 6),
                mutableListOf(1, 2, 3, 5),
                mutableListOf(0, 1, 3, 5, 6),
                mutableListOf(0, 1, 3, 4, 5, 6),
                mutableListOf(0, 2, 5),
                mutableListOf(0, 1, 2, 3, 4, 5, 6),
                mutableListOf(0, 1, 2, 3, 5, 6)
        )

        for (i in input) {

            val tempList = i.split("|")
            val signals = tempList.first().split(" ").filter { it.isNotBlank() }.map { it.replace(" ", "") }
            val digits = tempList.last().split(" ").filter { it.isNotBlank() }.map { it.replace(" ", "") }

            for (i in permutation(segmentDigits)) {
                val newDigitList = mutableListOf<String>()

                for (j in digitsList) {
                    var tempDigits = ""
                    for (k in j) tempDigits += i[k]
                    newDigitList.add(tempDigits)
                }

                var tempCount = 0
                for (j in signals) {
                    for (k in newDigitList) {
                        if (j.toSortedSet() == k.toSortedSet()) {
                            tempCount++
                        }
                    }
                }

                if (tempCount == 10) {
                    var result = ""
                    for (j in digits) {
                        for (k in newDigitList.indices) {
                            if (j.toSortedSet() == newDigitList[k].toSortedSet()) {
                                result += k
                            }
                        }
                    }
                    if (result.length == 4) {
                        finalResult += result.toInt()
                    }

                }
            }
        }

        return finalResult
    }

    val input = readInput("Day08")
    println("p1 result: ${part1(input)}")
    println("p2 result: ${part2(input)}")
}
