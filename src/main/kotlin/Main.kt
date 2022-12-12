import Game.Play.*
import java.io.File

fun main(args: Array<String>) {

    val input = (File("08input.txt").readLines())
    val test = """
30373
25512
65332
33549
35390
 """.trimIndent().split("\n")

//    val lines = test;
    val lines = input;

    fun isVisible(lines: List<String>, x: Int, y: Int) : Boolean{
        fun getHeight(x:Int,y:Int): Int{
            return lines[y][x].toString().toInt()
        }
        fun printVis(visible: Boolean, reason: String = "" ){
            println("char ${lines[y][x]} at $x, $y is visible: $visible $reason" )
        }
        val height = lines.size-1;
        val length = lines[0].length-1;
        val treeHeight = getHeight(x,y)
        if(x == 0 || x == length || y == 0 || y == height){
            return true;
        }
        val goingLeftRange = x-1 downTo 0
        val goingRightRange = x+1 .. length;
        val goingUpRange = y-1 downTo 0
        val goingDownRange = y +1 .. height



        if( goingLeftRange.all{ getHeight(it, y) < treeHeight } ) {
            printVis(true ,"left")
            return true
        }
        if( goingRightRange.all { getHeight(it, y) < treeHeight }) {
            printVis(true ,"right")
            return true
        }
        if( goingUpRange.all{ getHeight(x, it) < treeHeight } ) {
            printVis(true ,"up")
            return true
        }
        if( goingDownRange.all { getHeight(x, it) < treeHeight }) {
            printVis(true ,"down")
            return true
        }



        return false;
    }
    var i =0;
    lines.forEachIndexed { y: Int,  line :String ->
        println("new line")
        line.forEachIndexed{ x, char ->
            if (isVisible(lines, x,y )) {
                i++;
            }
        }
    }
    println(i)

}
