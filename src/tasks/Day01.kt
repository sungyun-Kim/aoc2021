fun main() {

    fun part1(input: List<String>): Int {
        var count = 0
        var before = input[0].toInt()
        var after: Int

        for (i in 1 until input.size) {
            after = input[i].toInt()
            if (before < after) count++
            before = after
        }

        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        var before = input[0].toInt() + input[1].toInt() + input[2].toInt()
        var after: Int

        for (i in 2 until input.size - 1) {
            after = input[i - 1].toInt() + input[i].toInt() + input[i + 1].toInt()
            if (before < after) count++
            before = after
        }

        return count
    }

    val input = readInput("Day01")

    println("p1 result: ${part1(input)}")
    println("p2 result: ${part2(input)}")
}
