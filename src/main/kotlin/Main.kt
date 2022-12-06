import java.io.File

import Game.Play.*
import kotlin.math.log

fun main(args: Array<String>) {

    val lines = (File("05input.txt").readLines())
//    val lines = """
//    [D]
//[N] [C]
//[Z] [M] [P]
// 1   2   3
//
//move 1 from 2 to 1
//move 3 from 1 to 3
//move 2 from 2 to 1
//move 1 from 1 to 2
// """.trimIndent().split("\n")


    val index = lines.indexOf("");
    val stacksLines = lines.subList(0,index - 1)
    val columnsLine = lines.subList(index-1, index)[0]
    val instructionsLines  = lines.subList(index+1, lines.lastIndex+1)

    val columns = columnsLine.split(" ").filter { it.isNotEmpty() }

    val stacks = columns.map {
        ArrayDeque<String>()
    }
    val rows = stacksLines.map{
        val regex = """....?""".toRegex()
        regex.findAll(it).map {
            it.value.trim()
        }.toList()

    }
    rows.forEach { row ->
        row.forEachIndexed { i, item ->
            if(!item.isEmpty()){
                stacks[i].add(item.substring(1,2));
            }
        }
    }

    println("Stacks: ${stacks}")
    val instructions = instructionsLines.map{
        val words = it.split(" ")
        listOf(words[1].toInt(), words[3].toInt()-1, words[5].toInt()-1)
    }.forEach{ instruction ->
        val (times, from, to ) = instruction;

        val items = stacks[from].subList(0,times).toList()
        println("items: $items")
        repeat(times){ stacks[from].removeFirst()}
        println("from: ${stacks[from]}")
        println("items: $items")
        stacks[to].addAll(0, items)
        println("items: $items")
        println("after move instruction ${instruction}: $stacks")
    }
    val result = stacks.map {
        it.removeFirst()
    }.joinToString("") { it }


    println("columns: ${result}")

}
