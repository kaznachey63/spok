package com.example.spok

enum class GameItems {
    ROCK, PAPER, SCISSORS, LIZARD, SPOCK, NONE;

    // Метод для определения победителя
    fun getOutcomeAgainst(other: GameItems): GameOutcome {
        return when (this) {
            ROCK -> when (other) {
                SCISSORS, LIZARD -> GameOutcome.WIN
                PAPER, SPOCK -> GameOutcome.LOSE
                else -> GameOutcome.DRAW
            }
            PAPER -> when (other) {
                ROCK, SPOCK -> GameOutcome.WIN
                SCISSORS, LIZARD -> GameOutcome.LOSE
                else -> GameOutcome.DRAW
            }
            SCISSORS -> when (other) {
                PAPER, LIZARD -> GameOutcome.WIN
                ROCK, SPOCK -> GameOutcome.LOSE
                else -> GameOutcome.DRAW
            }
            LIZARD -> when (other) {
                PAPER, SPOCK -> GameOutcome.WIN
                ROCK, SCISSORS -> GameOutcome.LOSE
                else -> GameOutcome.DRAW
            }
            SPOCK -> when (other) {
                SCISSORS, ROCK -> GameOutcome.WIN
                PAPER, LIZARD -> GameOutcome.LOSE
                else -> GameOutcome.DRAW
            }
            NONE -> GameOutcome.DRAW
        }
    }
}

enum class GameOutcome {
    WIN, LOSE, DRAW
}