package com.example.infectiontag

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
        var startGameButton = findViewById<Button>(R.id.startGameBtn)
        var endGameButton = findViewById<Button>(R.id.endGameBtn)
        var leaveGameButton = findViewById<Button>(R.id.leaveGameBtn)



        gameIDTextBox.setOnClickListener{
            println("Pressed")
        }

        joinGameButton.setOnClickListener{
            println("Pressed")
        }

        createGameButton.setOnClickListener{
            println("Pressed")
        }

        startGameButton.setOnClickListener{
            println("Pressed")
        }

        endGameButton.setOnClickListener{
            println("Pressed")
        }

        leaveGameButton.setOnClickListener{
            println("Pressed")
        }





    }
}