import Rope.Directions.*
import java.io.File

fun main(args: Array<String>) {

    val input = (File("09input.txt").readLines())
    val test = """
R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2
 """.trimIndent().split("\n")

//    val lines = test;
    val lines = input;
    val rope = Rope()

    lines.forEach {
        val (direction, distance ) = it.split(" ");
        val dir = valueOf(direction)
        rope.moveHead(dir, distance.toInt() )
    }
    println(rope.tailVisits )
    println(rope.tailVisits.size )
//    rope.moveHead(R, 3)
//    rope.moveHead(U, 2)
//    rope.moveHead(R, 4)
//
//    rope.moveHead(U, 4)
//    rope.moveHead(L, 4)
}
