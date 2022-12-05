import java.io.File

import Game.Play.*

fun main(args: Array<String>) {

    val lines = (File("04input.txt").readLines())
//    val lines = """
//2-4,6-8
//2-3,4-5
//5-7,7-9
//2-8,3-7
//6-6,4-6
//2-6,4-8
// """.trimIndent().split("\n")

    val assignments = lines.map{ it.split(",") }
        .map{ assignments ->
        assignments.map{  assignment -> assignment.split('-').map{it.toInt()}}
    }

    val filtered = assignments.filter { pairs ->
        val (firstPair, secondPair) = pairs;
        val (first, second) = firstPair;
        val (third, fourth) = secondPair;
        val firstRange = (first .. second).toSet()
        val secondRange = (third .. fourth).toSet()
        firstRange.intersect(secondRange).size != 0
    }

    println("results: ${filtered}, ${filtered.size}")

}
