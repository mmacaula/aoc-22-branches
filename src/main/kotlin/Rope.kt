class Rope (){
    val startingTailPosition =  0 to 0
    var currentHeadPosition = startingTailPosition
    var currentTailPosition = startingTailPosition
    val tailVisits = mutableSetOf<Pair<Int,Int>>(startingTailPosition)
    enum class Directions {
        R, U, D, L
    }


    fun cardinalDistance(p1: Pair<Int,Int> = currentHeadPosition, p2: Pair<Int,Int> = currentTailPosition) : Int {
        if(p1.first == p2.first){
            return Math.abs(p1.second - p2.second)
        }else if(p1.second == p2.second) {
            return Math.abs(p1.first - p2.first)
        }
        return -1
    }

    fun isSimpleDiag(p1: Pair<Int,Int> = currentHeadPosition, p2: Pair<Int,Int> = currentTailPosition) : Boolean {
        return Math.abs(p1.first - p2.first) == 1 && Math.abs(p1.second - p2.second) == 1
    }


    fun moveTail(direction: Directions){
        val cardDistance = cardinalDistance();
        if(cardDistance == -1){
            if(!isSimpleDiag()){
                when(direction){
                    Directions.R -> currentTailPosition = currentHeadPosition.first - 1 to currentHeadPosition.second
                    Directions.L -> currentTailPosition = currentHeadPosition.first + 1 to currentHeadPosition.second
                    Directions.U -> currentTailPosition = currentHeadPosition.first  to currentHeadPosition.second -1
                    Directions.D -> currentTailPosition = currentHeadPosition.first  to currentHeadPosition.second +1
                }
                println("need to move tail")
            }
            println("simple diagonal, doing nothing")

        }else if(cardDistance == 2){
            when(direction){
                Directions.R -> currentTailPosition = currentTailPosition.first + 1 to currentTailPosition.second
                Directions.L -> currentTailPosition = currentTailPosition.first - 1 to currentTailPosition.second
                Directions.U -> currentTailPosition = currentTailPosition.first  to currentTailPosition.second +1
                Directions.D -> currentTailPosition = currentTailPosition.first  to currentTailPosition.second -1
            }
        }else{
            println("distance was not diagonal or 2 (it was ${cardDistance}, doing nothing")
        }
        println("current tail after head moved $direction: ${currentTailPosition}")
        tailVisits.add(currentTailPosition.copy())
    }

    fun moveHead(direction: Directions){
//        println("current head before moving $direction: ${currentHeadPosition}")
        when (direction){
            Directions.R -> currentHeadPosition = currentHeadPosition.first + 1 to currentHeadPosition.second
            Directions.L -> currentHeadPosition = currentHeadPosition.first - 1 to currentHeadPosition.second
            Directions.U -> currentHeadPosition = currentHeadPosition.first  to currentHeadPosition.second +1
            Directions.D -> currentHeadPosition = currentHeadPosition.first  to currentHeadPosition.second -1
        }

        println("current head after moving $direction: ${currentHeadPosition}")
        moveTail(direction)
    }
    fun moveHead(direction : Directions, distance: Int) {
        repeat(distance) { moveHead(direction) }
    }


}
