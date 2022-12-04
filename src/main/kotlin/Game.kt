

class Game {

    enum class Play( val elfPlay: String, val myPlay: String, val point : Int) {
        ROCK("A", "X", 1),
        PAPER("B", "Y", 2),
        SCISSORS("C", "Z", 3);

        companion object {
            fun getPlay(value: String) : Play {
                return values().find {
                    it.elfPlay == value || it.myPlay == value
                }!!
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
