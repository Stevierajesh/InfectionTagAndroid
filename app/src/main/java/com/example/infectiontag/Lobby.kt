package com.example.infectiontag
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Lobby : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lobby)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val game = GameRepository.game


        val startButton = findViewById<Button>(R.id.startGameButton)

        if(game?.admin == true){
            startButton.visibility= View.VISIBLE
        } else {
            startButton.visibility= View.GONE
        }

        startButton.setOnClickListener {
            if(game == null){
                Log.d("WS_IN", "CANNOT START GAME, GAME DOESN'T EXIST. REDIRECTING")
                val intent = Intent(this, GameIndex::class.java)
                startActivity(intent)
            } else {
                startGame()
            }
        }

    }

    fun startGame(){
        val game = GameRepository.game
        game?.setOnGameStartedListener {
            startActivity(Intent(this, GameView::class.java))
        }

        game?.startGame()

    }

}