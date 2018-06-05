package com.example.dgnanaratnam.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var ticTacToeGame: TicTacToe = TicTacToe(3)
    private var ticTacToeCells: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table: TableLayout = findViewById(R.id.tableLayout)
        for(i in 0..2) {
            var currentRow = TableRow(this)
            for(j in 0..2) {
                val tickTackToeCell = Button(this)
                tickTackToeCell.setOnClickListener(ticTacToeCellClick)
                ticTacToeCells.add(tickTackToeCell)
                currentRow.addView(tickTackToeCell)
            }
            table.addView(currentRow)
        }

        findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
    }

    private val ticTacToeCellClick: View.OnClickListener = View.OnClickListener{ view ->
        val ticTacToeCell: Button = view as Button
        val tableRow: TableRow =  ticTacToeCell.parent as TableRow
        val table: TableLayout = tableRow.parent as TableLayout

        // TODO: Find a better way to get cell row and cell column
        var cellRow: Int = table.indexOfChild(tableRow)
        var cellColumn: Int = tableRow.indexOfChild(ticTacToeCell)

        ticTacToeCell.isEnabled = false
        ticTacToeCell.text = if(ticTacToeGame.currentPlayer == 1) "X" else "O"
        ticTacToeGame.numMovesMade++

        val result = ticTacToeGame.move(cellRow,cellColumn)
        when {
            result == 1 -> {
                enableTicTacToeCells(false)
                findViewById<TextView>(R.id.winnerInfo).text = "Player 1 wins"

            }
            result == 2 -> {
                enableTicTacToeCells(false)
                findViewById<TextView>(R.id.winnerInfo).text = "Player 2 wins"
            }
            ticTacToeGame.numMovesMade == 9 -> {
                findViewById<TextView>(R.id.winnerInfo).text = "Game Tied"
            }
            else -> {
                ticTacToeGame.currentPlayer = if (ticTacToeGame.currentPlayer == 1) 2 else 1
                findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
            }
        }
    }
    fun resetClick(view: View) {
        enableTicTacToeCells(true)
        findViewById<TextView>(R.id.winnerInfo).text = ""
        ticTacToeCells.forEach({ it.text = "" })
        ticTacToeGame = TicTacToe(3)
        findViewById<TextView>(R.id.turn).text = "Turn: Player ${ticTacToeGame.currentPlayer}"
    }

    private fun enableTicTacToeCells(enable: Boolean) = ticTacToeCells.forEach{it.isEnabled = enable}

}

