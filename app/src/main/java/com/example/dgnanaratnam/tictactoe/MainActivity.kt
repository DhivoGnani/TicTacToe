package com.example.dgnanaratnam.tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var game: TicTacToe = TicTacToe(3)
    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null
    private var button6: Button? = null
    private var button7: Button? = null
    private var button8: Button? = null
    private var button9: Button? = null

    private var ticTacToeSpaces: MutableList<Button> = mutableListOf()


    var clicks: View.OnClickListener = View.OnClickListener {
        fun onClick(v: View) {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for(i in 0..2) {
            var currentRow: TableRow = TableRow(this)
            for(j in 0..2) {
                var ticTacToeSpace: Button = Button(this)
                ticTacToeSpace.setOnClickListener(clicks)
            }
        }

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        findViewById<TextView>(R.id.turn).text = "Turn: Player ${game.currentPlayer}"
    }


    fun onClick(view: View) {
        var row: Int = 0
        var col: Int = 0

        when(view.id) {
            R.id.button1 -> {
                row = 0
                col = 0
            }
            R.id.button2 -> {
                row = 0
                col = 1
            }
            R.id.button3 ->  {
                row = 0
                col = 2
            }
            R.id.button4 ->  {
                row = 1
                col = 0
            }
            R.id.button5 ->
            {
                row = 1
                col = 1
            }
            R.id.button6 ->
            {
                row = 1
                col = 2
            }
            R.id.button7 ->  {
                row = 2
                col = 0
            }
            R.id.button8 ->  {
                row = 2
                col = 1
            }
            R.id.button9 ->  {
                row = 2
                col = 2
            }
        }


        var currentButton: Button = view as Button
        currentButton.isEnabled = false
        currentButton.text = if(game.currentPlayer == 1) "X" else "O"
        game.numMovesMade++
        val result = game.move(row,col)
        when {
            result == 1 -> {
                enableSpaces(false)
                findViewById<TextView>(R.id.winnerInfo).setText("Player 1 wins")

            }
            result == 2 -> {
                enableSpaces(false)
                findViewById<TextView>(R.id.winnerInfo).setText("Player 2 wins")
            }
            game.numMovesMade == 9 -> {

                // TODO: reset game
                findViewById<TextView>(R.id.winnerInfo).setText("Game Tied")
            }
            else -> {
                game.currentPlayer = if (game.currentPlayer == 1) 2 else 1
                findViewById<TextView>(R.id.turn).text = "Turn: Player ${game.currentPlayer}"
            }
        }

    }

    private fun enableSpaces(enable: Boolean) {
        button1?.isEnabled = enable
        button2?.isEnabled = enable
        button3?.isEnabled = enable
        button4?.isEnabled = enable
        button5?.isEnabled = enable
        button6?.isEnabled = enable
        button7?.isEnabled = enable
        button8?.isEnabled = enable
        button9?.isEnabled = enable
    }
    fun resetClick(view: View) {
        enableSpaces(true)
        findViewById<TextView>(R.id.winnerInfo).text = ""
        button1?.text = ""
        button2?.text = ""
        button3?.text = ""
        button4?.text = ""
        button5?.text = ""
        button6?.text = ""
        button7?.text = ""
        button8?.text = ""
        button9?.text = ""
        game = TicTacToe(3)
        findViewById<TextView>(R.id.turn).text = "Turn: Player ${game.currentPlayer}"
    }


}
