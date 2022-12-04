import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")
    val lines = (File("01input.txt").readLines())
//    {
//        File("01input.txt").forEachLine {
//        println(it)
//    }
//    lines

    val groupedByElf : ArrayList<ArrayList<String>> = lines.fold(
        ArrayList(ArrayList()),
        fun(acc: ArrayList<ArrayList<String>>, line: String): ArrayList<ArrayList<String>> {
            if(acc.isEmpty()){
                acc.add(ArrayList())
            }
            val last = acc.last();

            println(last)
            if(line == ""){
                acc.add(ArrayList())
            }else {
                last.add(line)
                println(last)
            }
            return acc
        })

    val sumCalories = groupedByElf.map {
        it.sumOf { it.toInt() }
    }.sortedBy{ it }
        .reversed()
        .subList(0,3)
        .sum()


    println("results = $sumCalories")
}
