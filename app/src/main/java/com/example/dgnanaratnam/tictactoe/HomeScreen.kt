package com.example.dgnanaratnam.tictactoe

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HomeScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
    }

    fun startGame(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
