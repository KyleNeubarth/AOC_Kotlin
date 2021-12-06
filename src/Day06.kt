open class Fish {
    var timer:Int = 8
    var isNew:Boolean = true
}

fun main() {
    //naive approach
    fun part1(input: List<String>, numDays:Int): Int {
        val splitInput = input[0].split(",").map {it.toInt()}
        val school = MutableList(splitInput.size) {Fish()}
        for (i in 0..splitInput.size-1) {
            school[i].timer = splitInput[i]
            school[i].isNew = false
        }
        for (d in 1..numDays) {
            //print("Day "+d+": "+school.size+" {")
            //school.forEach{ print(it.timer) }
            //println("} ")
            for (i in 0 until school.size) {
                if (school[i].timer <= 0) {
                    //println("Fish "+i+" spawned a new fish")
                    school.add(Fish())
                    school[i].timer = 6
                } else {
                    school[i].timer--
                }
            }
        }
            return school.size
    }
    //less naive approach
    fun part2(input: List<String>, numDays:Int): Long {
        val splitInput = input[0].split(",").map {it.toInt()}
        val school = Array(7) {0L}
        val newSchool = Array(9) {0L}
        for (i in 0..splitInput.size-1) {
            school[splitInput[i]]++
        }
        for (d in 0 until numDays) {
            //var printSum = 0
            //println("---school---")
            for (i in 0 until school.size) {
                var timer = i-(d%7)
                if (timer < 0) timer+=7
                //println("Timer: "+timer+"; frequency: "+school[i])
                //printSum+=school[i]
            }
            //println("----newSchool----")
            for (i in 0 until newSchool.size) {
                var timer = i-(d%9)
                if (timer < 0) timer+=9
                //println("Timer: "+timer+"; frequency: "+newSchool[i])
                //printSum+=newSchool[i]
            }
            //println("Total fish: "+printSum)
            var tempToAdd = -1L
            if (newSchool[d%9] > 0) {
                tempToAdd = newSchool[d%9]
                //newSchool[d%9] = 0
            }
            if (school[d%7] > 0) {
                newSchool[d%9]+=school[d%7]
            }
            if (tempToAdd != -1L) {
                school[d%7]+=tempToAdd
                //newSchool[d%9]+=tempToAdd
            }
        }
        var sum:Long = 0
        for (i in 0 until school.size) {
            sum+=school[i]
        }
        for (i in 0 until newSchool.size) {
            sum+=newSchool[i]
        }
        return sum
    }

    val testInput = readInput("Day06_testinput")
    val input = readInput("Day06_input")

    val answer = part1(testInput,18)
    println("Part 1 test answer: "+answer)
    check(answer == 26)
    println("Part 1 answer: "+part1(input,80))

    val answer2 = part2(testInput,256)
    println("Part 2 test answer: "+answer2)
    println("Part 2 answer: "+part2(input,256))
    check(answer2 ==26984457539)
}
