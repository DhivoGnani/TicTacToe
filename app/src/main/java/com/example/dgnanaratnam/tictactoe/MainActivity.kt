package com.example.dgnanaratnam.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var gamesWonByPlayerOne = 0
    private var gamesWonByPlayerTwo = 0
    private var numTicTacRowsAndCols = 3
    private var ticTacToeGame: TicTacToe = TicTacToe(numTicTacRowsAndCols)
    private var ticTacToeCells: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table: TableLayout = findViewById(R.id.tableLayout)
        for(i in 0 until numTicTacRowsAndCols) {
            var currentRow = TableRow(this)
            for(j in 0 until numTicTacRowsAndCols) {
                val tickTackToeCell = Button(this)
                tickTackToeCell.setOnClickListener(ticTacToeCellListener)
                ticTacToeCells.add(tickTackToeCell)
                currentRow.addView(tickTackToeCell)
            }
            table.addView(currentRow)
        }

        findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
        findViewById<TextView>(R.id.playerOneScore).text = "Player 1 Score: $gamesWonByPlayerOne"
        findViewById<TextView>(R.id.playerTwoScore).text = "Player 2 Score: $gamesWonByPlayerTwo"
    }

    private val ticTacToeCellListener: View.OnClickListener = View.OnClickListener{ view ->
        ticTacToeCellClick(view)
    }

    private fun ticTacToeCellClick(view: View?) {
        val ticTacToeCell: Button = view as Button
        val tableRow: TableRow = ticTacToeCell.parent as TableRow
        val table: TableLayout = tableRow.parent as TableLayout

        // TODO: Find a better way to get cell row and cell column
        var cellRow: Int = table.indexOfChild(tableRow)
        var cellColumn: Int = tableRow.indexOfChild(ticTacToeCell)

        ticTacToeCell.isEnabled = false
        ticTacToeCell.text = if (ticTacToeGame.currentPlayer == 1) "X" else "O"

        val result = ticTacToeGame.move(cellRow, cellColumn)
        when {
            result == 1 -> {
                gamesWonByPlayerOne++
                enableTicTacToeCells(false)
                findViewById<TextView>(R.id.winnerInfo).text = "Player 1 wins"
                findViewById<TextView>(R.id.playerOneScore).text = "Player 1 Score: $gamesWonByPlayerOne"
            }
            result == 2 -> {
                gamesWonByPlayerTwo++
                enableTicTacToeCells(false)
                findViewById<TextView>(R.id.winnerInfo).text = "Player 2 wins"
                findViewById<TextView>(R.id.playerTwoScore).text = "Player 2 Score: $gamesWonByPlayerTwo"
            }
            ticTacToeGame.gameOver() -> {
                findViewById<TextView>(R.id.winnerInfo).text = "Game Tied"
            }
            else -> {
                findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
            }
        }
    }

    fun resetClick(view: View) {
        enableTicTacToeCells(true)
        findViewById<TextView>(R.id.winnerInfo).text = ""
        ticTacToeCells.forEach({ it.text = "" })
        ticTacToeGame = TicTacToe(numTicTacRowsAndCols)
        findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
    }

    private fun enableTicTacToeCells(enable: Boolean) = ticTacToeCells.forEach{it.isEnabled = enable}

}
