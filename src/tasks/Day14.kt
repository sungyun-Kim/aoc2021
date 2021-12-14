fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        var beforeSubject = input.first()
        val cmdMap = mutableMapOf<String, String>()

        for (i in 2 until input.size) {
            val temp = input[i].replace(" ", "").split("->").filter { it.isNotBlank() }
            cmdMap[temp[0]] = temp[1]
        }

        val countList = MutableList(300) { 0 }

        //10회
        for (i in 0 until 40) {

            var tempCount = 0
            var afterSubject = ""

            for (j in 0 until beforeSubject.length - 1) {
                afterSubject += beforeSubject[j]
                if (cmdMap.containsKey(beforeSubject.substring(j, j + 2))) {
                    afterSubject += cmdMap[beforeSubject.substring(j, j + 2)]
                    tempCount++
                }
            }

            afterSubject += beforeSubject.last()
            beforeSubject = afterSubject
        }

        for (i in beforeSubject.indices) {
            countList[beforeSubject[i].toInt()]++
        }

        countList.removeAll { it == 0 }


        return countList.maxOf { it } - countList.minOf { it }
    }

    fun part2(input: List<String>): Int {
        var count = 0

        return count
    }

    val input = readInput("Day14")

    println("p1 result: ${part1(input)}")
    println("p2 result: ${part2(input)}")
}
