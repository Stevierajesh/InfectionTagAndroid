package com.example.infectiontag

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

/*
* DISCLAIMER! Any function that has to do with sending through the WebSocket MUST be stored here.
 */




class GameWebSocketManager {
    private val gson = Gson()


    private var webSocket: WebSocket? = null
    private val listeners = mutableListOf<GameMessageListener>()
    private val serverLink = "wss://domical-kasi-aguishly.ngrok-free.dev"

    var gameId: String? = null
        private set
    //private set means anyone can read, but no class may change it

    private var onGameCreated: ((String) -> Unit)? = null



    fun startConnection(macAddress: String) {
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url(serverLink)
            .build()

        webSocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                webSocket.send("HELLO $macAddress")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                // handle message, can do later, must add filtering for performance
                //Log.d("WS_IN", text)
                val data = gson.fromJson(text, GameCreatedMessage::class.java)
                gameId = data.gameID
                onGameCreated?.invoke(gameId!!)



                for (listener in listeners) {
                    listener.onGameMessage(text)
                }
            }

            override fun onFailure(
                webSocket: WebSocket,
                t: Throwable,
                response: Response?
            ) {
                t.printStackTrace()
            }
        })
    }

    fun send(message: String){
        webSocket?.send(message);

    }

    fun addListener(listener: GameMessageListener){
        listeners.add(listener)
    }

    fun remove(listener: GameMessageListener){
        listeners.remove(listener)
    }


}
