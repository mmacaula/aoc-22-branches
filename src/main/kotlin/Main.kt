import java.io.File

import Game.Play.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val lines = (File("02input.txt").readLines())

    val scores = lines.map {
        val (elf, me) = it.split(" ");
        val elfPlay = Companion.getPlay(elf)
        val myPlay = Companion.getPlay(me)
        println("$elfPlay: $myPlay")
        myPlay.score(elfPlay)
    }
    val total = scores.sum();


    println("results = $scores, total: $total")
}
