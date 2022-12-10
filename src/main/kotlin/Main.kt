import Game.Play.*
import java.io.File

fun main(args: Array<String>) {

    val input = (File("07input.txt").readLines())
    val test = """
30373
25512
65332
33549
35390
 """.trimIndent().split("\n")

    val lines = test;
//    val lines = input;

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
        for( i in x downTo 0){
            if( treeHeight < getHeight(i,y) ){
                return false
            }
        }


        return false;
    }
    lines.forEachIndexed { y: Int,  line :String ->
        println("new line")
        line.forEachIndexed{ x, char ->
            isVisible(lines, x,y )
        }
    }

}
