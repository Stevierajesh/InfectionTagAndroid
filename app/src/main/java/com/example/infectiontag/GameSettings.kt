package com.example.infectiontag

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GameSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val createGameButton = findViewById<Button>(R.id.createGameBtn)

        createGameButton.setOnClickListener {
            createGame()
        }

    }

    fun createGame(){

        val game = GameController()

        //game.createGame()

        game.createGame{ gameId ->
            val intent = Intent(this, Lobby::class.java)
            Log.d("WS_IN", "GAME ID GIVEN TO USA:" + gameId)
            game.updateGameCode(gameId)
            game.updateAdminStatus(true)
            GameRepository.game = game
            startActivity(intent)
        }
    }
}