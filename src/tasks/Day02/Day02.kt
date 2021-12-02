fun main() {

    data class Command(val c :String ,val i : Int )

    fun part1(input: List<Command>): Int {

        for (i in input){
            println(i)
        }

        var z = 0
        var y = 0
        var aim = 0

        for(i in input){
            if(i.c == "forward"){
                y += i.i
                z+= i.i * aim
            }
            else if(i.c == "down"){
                aim += i.i
            }
            else{
                //up
                aim -= i.i
            }
        }

        println("z: $z y: $y")

        return z*y
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day02")

    val inputList = mutableListOf<Command>()

    for (i in input) {

        val com = i.split(" ")

        inputList.add(Command(com[0],com[1].toInt()))
    }

    println(part1(inputList))
    println(part2(input))
}
