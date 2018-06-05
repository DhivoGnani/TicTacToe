package com.example.dgnanaratnam.tictactoe


class TicTacToe(private val numRowsAndCols: Int) {
    private var rows: IntArray = IntArray(numRowsAndCols)
    private var cols: IntArray = IntArray(numRowsAndCols)
    private var diagonal: Int = 0;
    private var antiDiagonal: Int = 0
    // Winning player is initially set to 0 (tied).
    private var winningPlayer: Int = 0

    private var numMovesMade: Int =0

    // The current play er, can be either 1 or 2.
    var currentPlayer: Int = 1



    /** Current player makes a move at ({row}, {col}).
     * @param row The row of the board.
     * @param col The column of the board.
     * @return The current winning condition, can be either:
     * 0: No one wins.
     * 1: Player 1 wins.
     * 2: Player 2 wins.
     */
    fun move(row: Int, col: Int): Int {
        numMovesMade++

        val toAdd = if (currentPlayer == 1) 1 else -1

        rows[row] += toAdd
        cols[col] += toAdd
        if (row == col) {
            diagonal += toAdd
        }

        if (col == cols.size - row - 1) {
            antiDiagonal += toAdd
        }

        val size = rows.size
        winningPlayer = if (Math.abs(rows[row]) == size ||
                Math.abs(cols[col]) == size ||
                Math.abs(diagonal) == size ||
                Math.abs(antiDiagonal) == size) {
            currentPlayer
        } else 0

        switchCurrentPlayer()
        return winningPlayer
    }

    fun gameOver(): Boolean = numMovesMade == numRowsAndCols * numRowsAndCols|| winningPlayer != 0

    private fun switchCurrentPlayer(){
        currentPlayer = if(currentPlayer == 1)  2  else  1
    }
}