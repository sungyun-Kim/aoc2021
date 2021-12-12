fun main() {

    fun part1(input: List<String>): Int {
        var count = 0

        val dx = mutableListOf(-1, 0, 1, -1, 1, -1, 0, 1)
        val dy = mutableListOf(1, 1, 1, 0, 0, -1, -1, -1)

        val octopusMap = input.map {
            it.split("")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }.toMutableList()
        }.toMutableList()

        //100차례 시행
        for (n in 0 until 100) {

            //flashMap 초기화
            val flashMap = MutableList(octopusMap.size) { MutableList(octopusMap.first().size) { false } }

            //에너지준위 증가
            for (i in octopusMap.indices) {
                for (j in octopusMap[i].indices) {
                    octopusMap[i][j]++
                }
            }

            var flag = true

            while (flag) {
                for (i in octopusMap.indices) {
                    for (j in octopusMap[i].indices) {

                        //문어는 한 차례에 한 번만 깜빡일 수 있음
                        if (flashMap[i][j]) continue

                        //이 때 에너지 준위가 9가 초과일 시 (= 10이상) 섬광하며, 인접한(**대각선 포함**) 문어들의 에너지 준위를 1씩 올림
                        if (octopusMap[i][j] >= 10) {

                            //섬광 횟수 추가
                            count++

                            flashMap[i][j] = true
                            for (k in 0 until 8) {
                                val newY = i + dy[k]
                                val newX = j + dx[k]
                                if (newY < 0 || newX < 0 || newY >= octopusMap.size || newX >= octopusMap[0].size) continue
                                octopusMap[newY][newX]++
                            }
                        }
                    }
                }
                flag = false
                //에너지 준위 10 이상의 문어가 존재하면 반복됨
                for (i in octopusMap.indices) {
                    for (j in octopusMap[i].indices) {
                        if (octopusMap[i][j] >= 10 && flashMap[i][j]) octopusMap[i][j] = 0
                        if (octopusMap[i][j] >= 10 && !flashMap[i][j]) flag = true
                    }
                }
            }
            //섬광했다면 에너지 준위 0
            for (i in flashMap.indices) {
                for (j in flashMap[i].indices) {
                    if (flashMap[i][j]) octopusMap[i][j] = 0
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {

        var result = 0

        val dx = mutableListOf(-1, 0, 1, -1, 1, -1, 0, 1)
        val dy = mutableListOf(1, 1, 1, 0, 0, -1, -1, -1)

        val octopusMap = input.map {
            it.split("")
                    .filter { it.isNotBlank() }
                    .map { it.toInt() }.toMutableList()
        }.toMutableList()

        var index = 0

        //동기화 될 때 까지
        while (result == 0) {
            index++
            //flashMap 초기화
            val flashMap = MutableList(octopusMap.size) { MutableList(octopusMap.first().size) { false } }
            //에너지준위 증가
            for (i in octopusMap.indices) {
                for (j in octopusMap[i].indices) {
                    octopusMap[i][j]++
                }
            }
            var flag = true
            while (flag) {
                for (i in octopusMap.indices) {
                    for (j in octopusMap[i].indices) {
                        //문어는 한 차례에 한 번만 깜빡일 수 있음
                        if (flashMap[i][j]) continue
                        //이 때 에너지 준위가 9가 초과일 시 (= 10이상) 섬광하며, 인접한(**대각선 포함**) 문어들의 에너지 준위를 1씩 올림
                        if (octopusMap[i][j] >= 10) {
                            flashMap[i][j] = true
                            for (k in 0 until 8) {
                                val newY = i + dy[k]
                                val newX = j + dx[k]
                                if (newY < 0 || newX < 0 || newY >= octopusMap.size || newX >= octopusMap[0].size) continue
                                octopusMap[newY][newX]++
                            }
                        }
                    }
                }
                flag = false
                //에너지 준위 10 이상의 문어가 존재하면 반복됨
                for (i in octopusMap.indices) {
                    for (j in octopusMap[i].indices) {
                        if (octopusMap[i][j] >= 10 && flashMap[i][j]) octopusMap[i][j] = 0
                        if (octopusMap[i][j] >= 10 && !flashMap[i][j]) flag = true
                    }
                }
            }
            //섬광했다면 에너지 준위 0
            for (i in flashMap.indices) {
                for (j in flashMap[i].indices) {
                    if (flashMap[i][j]) octopusMap[i][j] = 0
                }
            }

            //동기화 시 탈출
            if (octopusMap.sumOf { it -> it.sumOf { it } } == 0) result = index
        }

        return result
    }

    val input = readInput("Day11")

    println("p1 result: ${part1(input)}")
    println("p2 result: ${part2(input)}")
}
