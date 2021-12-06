import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): Int {

        val fishList = input[0].split(",").map { it.toInt() }
        val tempList = MutableList(9) { 0 }

        for (i in 1..80) {

            for (j in 0..8) {
                if(j == 0){

                }
            }

        }
        return 0
    }

    fun part2(input: List<String>): BigInteger {

        val bigIntegerInit = 0.toBigInteger()
        val fishList = input[0].split(",").map { it.toInt() }
        val tempList = MutableList(9) { bigIntegerInit }

        for (j in fishList) {
            for (i in 0..8) {
                if (j == i) {
                    tempList[i]++
                }
            }
        }

        for (i in 1..256) {
            val temp = tempList.first()
            if (temp > bigIntegerInit) {
                tempList[7] += temp
            }
            tempList.add(temp)
            tempList.removeFirst()
        }

        var sum: BigInteger = bigIntegerInit

        for (i in tempList) {
            sum += i
        }

        return sum
    }

    val input = readInput("Day06")

    println(part1(input))
    println(part2(input))
}
