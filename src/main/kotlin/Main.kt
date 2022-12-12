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

    fun getXDistance(lines: List<String>, x: Int, y: Int, range: IntProgression, reason : String): Int {
        fun getHeight(x:Int,y:Int): Int{
            return lines[y][x].toString().toInt()
        }
        fun printDistance(distance: Int, ){
            println("char ${lines[y][x]} at $x, $y has distance: $distance $reason" )
        }
        val treeHeight = getHeight(x,y)
        var distance = 0;
        range.forEachIndexed { index, i ->
            if (getHeight(i, y) < treeHeight ){
                distance++
            }
            else{
                println("getHeight($i, $y) < treeHeight: ${getHeight(i, y)} < $treeHeight")
                distance++
                printDistance(distance)
                return distance
            }
        }
        printDistance(distance)
        return distance
    }

    fun getYDistance(lines: List<String>, x: Int, y: Int, range: IntProgression, reason:String): Int {
        fun getHeight(x:Int,y:Int): Int{
            return lines[y][x].toString().toInt()
        }
        fun printDistance(distance: Int){
            println("char ${lines[y][x]} at $x, $y has distance: $distance $reason" )
        }
        val treeHeight = getHeight(x,y)
        var distance = 0;
        println(range)
        range.forEachIndexed { index, i ->
            if (getHeight(x, i) < treeHeight ){
//                println("getHeight($x, $i) < treeHeight: ${getHeight(x, i)} < $treeHeight")
                distance++
            }
            else{
                println("getHeight($x, $i) < treeHeight: ${getHeight(x, i)} < $treeHeight")
                distance++
                printDistance(distance)
                return distance
            }
        }
        printDistance(distance)
        return distance
    }

    fun isVisible(lines: List<String>, x: Int, y: Int) : Int{
        fun getHeight(x:Int,y:Int): Int{
            return lines[y][x].toString().toInt()
        }
        fun printVis(visible: Boolean, reason: String = "" ){
            println("char ${lines[y][x]} at $x, $y is visible: $visible $reason" )
        }
        val height = lines.size-1;
        val length = lines[0].length-1;
        val treeHeight = getHeight(x,y)
        val goingLeftRange = x-1 downTo 0
        val goingRightRange = x+1 .. length;
        val goingUpRange = y-1 downTo 0
        val goingDownRange = y +1 .. height

        return getXDistance(lines, x, y, goingLeftRange, "left") * getXDistance(lines, x, y, goingRightRange, "right") * getYDistance(lines, x, y, goingUpRange, "up") * getYDistance(lines, x, y, goingDownRange, "down")
//        if( goingLeftRange.all{ getHeight(it, y) < treeHeight } ) {
//            printVis(true ,"left")
//            return true
//        }
//        if( goingRightRange.all { getHeight(it, y) < treeHeight }) {
//            printVis(true ,"right")
//            return true
//        }
//        if( goingUpRange.all{ getHeight(x, it) < treeHeight } ) {
//            printVis(true ,"up")
//            return true
//        }
//        if( goingDownRange.all { getHeight(x, it) < treeHeight }) {
//            printVis(true ,"down")
//            return true
//        }



    }
    var max =0;


    val scores = lines.forEachIndexed { y: Int,  line :String ->
        println("new line")
        line.forEachIndexed{ x: Int, char ->
            val score = isVisible(lines, x, y)
            if(score > max) {
                max = score
            }
        }
    }

    println(max)

}
