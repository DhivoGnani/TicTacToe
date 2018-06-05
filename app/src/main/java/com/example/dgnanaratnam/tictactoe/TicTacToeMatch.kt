package com.example.dgnanaratnam.tictactoe


data class Player(var id: Int, var score: Int = 0)

class TicTacToeMatch(numRowsAndCols: Int = 3) {
    val playerOne: Player = Player(1)
    val playerTwo: Player = Player(2)
    var currentGame: TicTacToe = TicTacToe(numRowsAndCols)

    fun move() {

    }
}
