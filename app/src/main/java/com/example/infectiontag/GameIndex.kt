package com.example.infectiontag

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameIndex : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_index)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        var gameIDTextBox = findViewById< EditText>(R.id.gameCodeTextBox)
        var joinGameButton = findViewById<Button>(R.id.joinGameBtn)
        var createGameButton = findViewById<Button>(R.id.createGameBtn)




        joinGameButton.setOnClickListener{
            val gameCode = gameIDTextBox.text.toString()
            if (gameCode.isEmpty()) return@setOnClickListener
            joinGame(gameCode)

        }



        createGameButton.setOnClickListener{
            val intent = Intent(this, GameSettings::class.java)
            startActivity(intent)
        }

    }

    fun joinGame(code : String) {
        val game = GameController()
        game.joinGame(code)
        GameRepository.game = game
        val intent = Intent(this, GameView::class.java)
        startActivity(intent)
    }
}