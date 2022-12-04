

class Game {

    enum class Play( val elfPlay: String, val point : Int) {
        ROCK("A", 1),
        PAPER("B", 2),
        SCISSORS("C", 3);

        companion object {
            fun getPlay(value: String) : Play {
                return values().find {
                    it.elfPlay == value;
                }!!
            }
            fun getStrategyPlay(elfPlay: Play, value : String) : Play {
                return values().find(fun(it: Play):Boolean {
                    if (value == "X") {
                        return !it.isTie(elfPlay) && !it.wins(elfPlay)
                    }
                    if (value == "Y") {
                        return it.isTie(elfPlay)
                    }
                    if (value == "Z") {
                        return it.wins(elfPlay)
                    }
                    return false;
                })!!
            }
        }

        fun isTie(otherPlay: Play): Boolean {
            return !otherPlay.wins(this) && !this.wins(otherPlay)
        }

        fun wins(otherPlay: Play): Boolean{
            return when(this){
                ROCK -> {
                    when (otherPlay) {
                        PAPER -> false
                        SCISSORS -> true
                        ROCK -> false
                    }
                }
                PAPER -> {
                    when (otherPlay) {
                        ROCK -> true
                        SCISSORS -> false
                        PAPER -> false
                    }
                }
                SCISSORS -> {
                    when (otherPlay) {
                        PAPER -> true
                        ROCK -> false
                        SCISSORS -> false
                    }
                }
            }
        }
        fun score( myPlay: Play): Int {
            val myScore = point;
            println("myscore: $myScore")
            if(this.isTie(myPlay)){
                println("is tie")
                return myScore + 3;
            }
            if( this.wins(myPlay)) {
                println("wins!")
                return myScore + 6;
            }
            println("loses!")
            return myScore + 0;
        }
    }
}
