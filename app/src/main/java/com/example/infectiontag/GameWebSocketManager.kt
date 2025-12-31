package com.example.infectiontag

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

/*
* DISCLAIMER! Any function that has to do with sending through the WebSocket MUST be stored here.
 */




class GameWebSocketManager {


    private var webSocket: WebSocket? = null
    private val serverLink = "ws://YOUR_IP:PORT"

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
                // handle message
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

    //fun sendLocation{}

}
