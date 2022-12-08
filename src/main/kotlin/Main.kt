import Game.Play.*
import java.io.File

fun main(args: Array<String>) {

    val lines = (File("07input.txt").readLines())
//    val lines = """
//${'$'} cd /
//${'$'} ls
//dir a
//14848514 b.txt
//8504156 c.dat
//dir d
//${'$'} cd a
//${'$'} ls
//dir e
//29116 f
//2557 g
//62596 h.lst
//${'$'} cd e
//${'$'} ls
//584 i
//${'$'} cd ..
//${'$'} cd ..
//${'$'} cd d
//${'$'} ls
//4060174 j
//8033020 d.log
//5626152 d.ext
//7214296 k
// """.trimIndent().split("\n")

    // cd commands and ls commands , ls commands have output to parse
    // commands start with $
    // output
    // a : { e: { i : 584} }, f : size,

    var dirStack = ArrayDeque<Entry>()
    var currentDir = Entry(name="root", dir= HashMap())

    dirStack.addLast(currentDir);
    val rootDir = currentDir
    lines.forEach {  line ->
        println("line: ${line}")
        if(line.startsWith('$')){
            println("command: ")
            println("current dir before processing command is ${currentDir.name}")
            if(line.contains("cd /")){
                println("going to root")
                dirStack.clear();
                dirStack.addLast(rootDir);
                currentDir = rootDir;
            }else if (line.contains("cd ..")){
                println("before cding up a level: ${dirStack.map{it.name}} dirStack is size ${dirStack.size}, currentDir is ${currentDir.name}")
                dirStack.removeLast()
                currentDir = dirStack.last()
                println("cding up a level: ${dirStack.map{it.name}} dirStack is size ${dirStack.size}, currentDir is ${currentDir.name}")
            }else if (line.contains("cd ")){
                val targetDir = line.substring(line.indexOf("cd ")+3);
                val newDir = currentDir.dir!!.get(targetDir)!!
                dirStack.addLast(newDir)
                currentDir = newDir;
                println("Current Dir is now: ${currentDir.name}")
                println("cding to ${targetDir}, ${dirStack.map{it.name}}")
            }
        } else {
            val (sizeOrDir, name) = line.split(" ")
            if(sizeOrDir == "dir"){
                val newDir = currentDir.dir!!.getOrPut(name) { Entry(name=name, dir = HashMap())}
                println("Added new dir ${name} to current dir ${currentDir.name}")
            }else {
                println("adding new file entry $name to current dir ${currentDir.name}")
                currentDir.dir?.put(name, Entry(name=name, file= sizeOrDir.toInt()))
            }
        }


    }

    fun calculateSize (entry: Entry): Int {
        if(entry.file != null){
            return entry.file
        }
        return entry.dir!!.entries.fold(0){ acc, mutableEntry ->
            acc + calculateSize(mutableEntry.value)
        }
    }

    fun walk(entry: Entry, adder: (entry: Entry) -> Int) {
        if(entry.file != null){
            adder(entry)
            return
        }
        adder(entry);
        return entry.dir!!.entries.forEach{walk(it.value, adder)}
    }

    val dirSizes = HashMap<String, Int>()
    walk(rootDir){ entry ->
        println("working on entry: ${entry.name}")
        val size = calculateSize(entry);

        if(entry.dir != null && size <= 100000){
            dirSizes.put(entry.name, size)
        }
        println("Size was $size")
        size
    }

    val totalSize = dirSizes.entries.sumOf { it -> it.value}

    println("dir size of ${dirSizes} is ${totalSize}")


//    println("columns: ${lines}")

}
