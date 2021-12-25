package tasks

import readInput
import readInputString
import java.io.File

fun main() {

    fun getEnhancedImage(algo: String, inputImage: List<String>): List<String> {

        val result = mutableListOf<String>()

        val dx = mutableListOf(-1, 0, 1, -1, 0, 1, -1, 0, 1)
        val dy = mutableListOf(-1, -1, -1, 0, 0, 0, 1, 1, 1)

        for (i in -1..inputImage.size) {

            var tempListString = ""

            for (j in -1..inputImage[0].length) {

                var tempString = ""
                //i,j이 정중앙인 위치의 3x3값에 해당하는 String 추가
                for (k in dx.indices) {
                    val tempX = j + dx[k]
                    val tempY = i + dy[k]

                    //범위 벗어나면
                    if (tempX < 0 || tempX >= inputImage[0].length || tempY < 0 || tempY >= inputImage.size) {
                        tempString += "0"
                        continue
                    }
                    //범위 안벗어나고 #이라면
                    if (inputImage[tempY][tempX] == '#') {
                        tempString += "1"
                    } else if (inputImage[tempY][tempX] == '.') {
                        tempString += "0"
                    }

                }
                tempListString += algo[Integer.parseInt(tempString, 2)]
            }
            result.add(tempListString)
        }

        return result
    }

    fun part1(input: String): Int {
        var count = 0

        val inputsplit = input.split("1")

        val enhancementAlgorithm = inputsplit.first()
        val inputImage = inputsplit.last().substring(1).split("\n").map { it.replace(" ", "") }

        val firstResult = getEnhancedImage(enhancementAlgorithm, inputImage)
        for (i in firstResult) {
            println(i)
        }
        println()
        val secondResult = getEnhancedImage(enhancementAlgorithm, firstResult)
        for (i in secondResult) {
            println(i)
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0

        return count
    }

    val input = readInputString("Day20")

    println("p1 result: ${part1(input)}")
}
