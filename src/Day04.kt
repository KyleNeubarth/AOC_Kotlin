class Bingo{
    val table:Array<IntArray> = Array(5) { IntArray(5)}
    val picked:Array<BooleanArray> = Array(5) { BooleanArray(5)}
    var won = false
    fun checkWin(): Boolean {
        for (i in 0..4) {
            var rowfalse = false
            for (j in 0..4) {
                if(picked[i][j] == false) rowfalse = true
            }
            if (!rowfalse) return true
        }
        for (j in 0..4) {
            var colfalse = false
            for (i in 0..4) {
                if(picked[i][j] == false) colfalse = true
            }
            if (!colfalse) return true
        }
        return false
    }
    fun sumUnmarked():Int {
        var sum = 0
        for (i in 0..4) {
            for (j in 0..4) {
                if (!picked[i][j]) sum+= table[i][j]
            }
        }
        return sum
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val nums: List<Int> =  input.get(0).split(",").map { it.toInt() }
        //nums.forEach { System.out.print(it.toString()+';') }
        //print('\n')
        val tables = Array((input.size-1)/6) {Bingo()}
        for (t in 0..tables.size-1) {
            for (i in 0..4) {
                val urow = input.get(2+t*6+i).split("\\s".toRegex())//.map { it.toInt()}
                val row = urow.filter { x -> x.length > 0 }.map {it.toInt()}
//                println(row)
                for (j in 0..4) {
                    tables[t].table[i][j]=row.get(j)
                }
            }
        }
        nums.forEach {
            for (t in 0..tables.size-1) {
                for (i in 0..4) {
                    for (j in 0..4) {
                        if (tables[t].table[i][j] == it) {
                            println("pulled number "+it+" matched  table "+t+" at "+i+","+j)
                            tables[t].picked[i][j] = true
                            if (tables[t].checkWin()) {
                                println("Table that won: "+t)
                                println("number that won: "+it)
                                val sum = tables[t].sumUnmarked()
                                println("sum on unmarked in winning table: "+sum)
                                return sum*it
                            }
                        }
                    }
                }
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val nums: List<Int> =  input.get(0).split(",").map { it.toInt() }
        //nums.forEach { System.out.print(it.toString()+';') }
        //print('\n')
        val tables = Array((input.size-1)/6) {Bingo()}
        for (t in 0..tables.size-1) {
            for (i in 0..4) {
                val urow = input.get(2+t*6+i).split("\\s".toRegex())//.map { it.toInt()}
                val row = urow.filter { x -> x.length > 0 }.map {it.toInt()}
//                println(row)
                for (j in 0..4) {
                    tables[t].table[i][j]=row.get(j)
                }
            }
        }
        var yetToWin = tables.size;
        nums.forEach {
            for (t in 0..tables.size-1) {
                for (i in 0..4) {
                    for (j in 0..4) {
                        if (tables[t].table[i][j] == it) {
                            //println("pulled number "+it+" matched  table "+t+" at "+i+","+j)
                            tables[t].picked[i][j] = true
                            if (tables[t].checkWin()) {
                                if (!tables[t].won) {
                                    yetToWin--
                                    tables[t].won = true
                                    println("Table "+t+"won!")
                                }
                                if (yetToWin==0) {
                                    println("Last able that won: "+t)
                                    println("Number that won: "+it)
                                    val sum = tables[t].sumUnmarked()
                                    println("Sum on unmarked in winning table: "+sum)
                                    return sum*it
                                }
                            }
                        }
                    }
                }
            }
        }

        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_testinput")
    val answer = part1(testInput)
    print(answer)
    check(answer == 4512)
    val input = readInput("Day04_input")
    println(part1(input))

    val answer2 = part2(testInput)
    println(answer2)
    check(answer2 ==1924)
    println(part2(input))
}
