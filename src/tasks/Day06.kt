import java.math.BigInteger

fun main() {

    fun part1(input: List<String>): Int {

        val fishList = input.first().split(",").map { it.toInt() }
        val tempList = mutableListOf<Int>()

        for(i in fishList){
            tempList.add(i)
        }

        for (i in 1..80) {

            for (j in tempList.indices) {
                if(tempList[j] == 0){
                    tempList[j] = 6
                    tempList.add(8)
                }
                else{
                    tempList[j]--
                }
            }
        }

        return tempList.size
    }

    fun part2(input: List<String>): BigInteger {

        val bigIntegerInit = 0.toBigInteger()
        val fishList = input.first().split(",").map { it.toInt() }
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

        return tempList.fold(BigInteger.ZERO) { acc, e -> acc + e }
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
