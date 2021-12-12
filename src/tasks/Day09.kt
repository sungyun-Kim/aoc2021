import java.awt.Point
import java.util.*

fun main() {

    val dx = listOf(0, 0, -1, 1)
    val dy = listOf(1, -1, 0, 0)

    fun part1(input: List<String>): Int {
        var result = 0

        for (j in input.indices) {

            for (i in input[j].indices) {

                var lowFlag = true
                for (n1 in 0 until 4) {
                    val newX = i + dx[n1]
                    val newY = j + dy[n1]

                    if (newX < 0 || newY < 0 || newX >= input[0].length || newY >= input.size) {
                        continue
                    }

                    if (input[newY][newX].toInt() <= input[j][i].toInt()) {
                        lowFlag = false
                    }
                }

                if (lowFlag) result += ((input[j][i] - '0').toInt() + 1)
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {

        val checked = Array(input.size) { Array(input[0].length) { false } }
        val queue = LinkedList<Point>()
        val sizeList = mutableListOf<Int>()

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] - '0' != 9 && !checked[i][j]) {

                    queue.add(Point(i, j))
                    checked[i][j] = true
                    var count = 1

                    while (queue.isNotEmpty()) {

                        val tempLoc = queue.poll()

                        for (n1 in 0 until 4) {

                            val newX = tempLoc.x + dx[n1]
                            val newY = tempLoc.y + dy[n1]

                            if (newX < 0 || newY < 0 || newY >= input[0].length || newX >= input.size) continue
                            if (checked[newX][newY]) continue
                            if (input[newX][newY] - '0' != 9) {
                                queue.add(Point(newX, newY))
                                checked[newX][newY] = true
                                count++
                            }
                        }
                    }
                    sizeList.add(count)
                }
            }
        }

        val newResult = sizeList.sortedDescending()

        var result = 1

        for (i in 0..2) {
            result *= newResult[i]
        }

        return result
    }

    val input = readInput("Day09")
    println("p1 result: ${part1(input)}")
    println("p2 result: ${part2(input)}")
}
