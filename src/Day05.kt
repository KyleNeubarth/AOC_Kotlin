import kotlin.math.absoluteValue
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        val map = Array(1000) {IntArray(1000)}
        for (i in 0..input.size-1) {
            val parsing = (input[i].filter { !it.isWhitespace() }).split("->")
            val leftside = parsing[0].split(",")
            var x1=leftside[0].toInt()
            var y1=leftside[1].toInt()

            val rightside = parsing[1].split(",")
            var x2=rightside[0].toInt()
            var y2=rightside[1].toInt()
            if (y1 > y2) {
                val temp = y2
                y2 = y1
                y1 = temp
            }
            if (x1 > x2) {
                val temp = x2
                x2 = x1
                x1 = temp
            }
//            println(""+x1+"; "+y1+"; "+x2+"; "+y2)
            if (x1==x2) {
                for (a in y1..y2) {
                    map[x1][a]++
                }
            } else if(y1==y2) {
                for (a in x1..x2) {
                    map[a][y1]++
                }
            } else {
//                println("this line is diagonal: "+input[i])
            }
        }
        var  numVents=0
        for (i in 0..999) {
            for (j in 0..999) {
                if (map[i][j] > 1) {
//                    println("overlap at "+i+"; "+j)
                    numVents++
                }
            }
        }

//        for (i in 0..9) {
//            for (j in 0..9) {
//                print(map[i][j])
//            }
//            println()
//        }

        println(numVents)
        return numVents
    }

    fun part2(input: List<String>): Int {
        val map = Array(1000) {IntArray(1000)}
        for (i in 0..input.size-1) {
            val parsing = (input[i].filter { !it.isWhitespace() }).split("->")
            val leftside = parsing[0].split(",")
            var x1=leftside[0].toInt()
            var y1=leftside[1].toInt()

            val rightside = parsing[1].split(",")
            var x2=rightside[0].toInt()
            var y2=rightside[1].toInt()
//            println(""+x1+"; "+y1+"; "+x2+"; "+y2)
            val dx =(x2-x1).absoluteValue
            val dy =(y2-y1).absoluteValue
//            println("dx: "+dx+"; dy: "+dy+"; val: "+(dx>dy))
            if (dx > dy) {
                if (x1>x2) {
                    var temp = x2
                    x2=x1
                    x1=temp
                    temp=y2
                    y2=y1
                    y1=temp
                }
                for (a in x1..x2) {
//                    println("fracx: "+((a-x1).toFloat()/(dx)))
                    map[a][(y1+(y2-y1)*((a-x1).toFloat()/(dx))).roundToInt()]++
                }
            } else {
                if (y1>y2) {
                    var temp = x2
                    x2=x1
                    x1=temp
                    temp=y2
                    y2=y1
                    y1=temp
                }
                for (a in y1..y2) {
//                    println("fracy: "+((a-y1).toFloat()/(dy)))
                    map[(x1+(x2-x1)*((a-y1).toFloat()/(dy))).roundToInt()][a]++
                }
            }

        }
        var  numVents=0
        for (i in 0..999) {
            for (j in 0..999) {
                if (map[i][j] > 1) {
//                    println("overlap at "+i+"; "+j)
                    numVents++
                }
            }
        }

        for (i in 0..9) {
            for (j in 0..9) {
                print(map[j][i])
            }
            println()
        }

       return numVents
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_testinput")
    val answer = part1(testInput)
    print(answer)
//    check(answer == 5)
    val input = readInput("Day05_input")
    println(part1(input))

    val answer2 = part2(testInput)
    println(answer2)
    check(answer2 ==12)
    println(part2(input))
}
