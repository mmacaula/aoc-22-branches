import java.io.File

import Game.Play.*

fun main(args: Array<String>) {

    val lines = (File("05input.txt").readLines())
    val compartments = lines.map { line ->
        val first = line.substring(0, line.length / 2);
        val second = line.substring(line.length / 2)
//        println(line)
//        println("$first : $second")
        listOf(first, second)
    }

    val found = compartments.map{ (first, second) ->
       println("ummm $first : $second")
       first.find { firstChar ->
            val found = second.find { secondChar ->
                firstChar == secondChar
            }
            (found != null)
        }
    }

    val priorityMap = HashMap<Char,Int>()
    var value = 1;
    for (i in 'a' .. 'z'){
        priorityMap[i] = value++;
    }
    for (i in 'A' .. 'Z'){
        priorityMap[i] = value++;
    }
    println(priorityMap)
    val ranking = found.map {
        priorityMap[it]!!
    }.sum()
    println("results = $ranking")

}
