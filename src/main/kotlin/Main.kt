import Game.Play.*
import java.io.File

fun main(args: Array<String>) {

    val lines = (File("06input.txt").readLines())
//    val lines = """
//zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
// """.trimIndent().split("\n")

    val line = lines[0];
    val initial = line.substring(0,14);
    val rest = line.substring(14)
    var initialList = initial.toList()
    var deque = ArrayDeque<Char>(initialList)
    println(deque);
    rest.forEachIndexed{ i, char ->

        deque.removeFirst();
        deque.addLast(char)
        println("after processing $char, deque is $deque")
        if(deque.toSet().size == 14){
            println("Deque set: ${deque.toSet()}")
            println (i + 15 )
            return
        }
    }

    println(initialList)
//    val results = line.fold(ArrayDeque<String>()) { acc, c ->
//        if(acc.len)
//    }


    println("columns: ${rest}")

}
