package com.example.infectiontag
import android.util.Log
import com.google.gson.Gson
/*
This is for controlling the game (client side only)
 */


class GameController : GameMessageListener {
    private val gson = Gson()
    private var onGameCreated: ((String) -> Unit)? = null
    var gameid :String?  = null;
    val playerID = "ASjedkrh3809"
    var socket: GameWebSocketManager? = null

    init {
        socket?.addListener(this)
    }

    var admin = false

    fun updateGameCode(gameID: String){
        gameid=gameID
    }

    fun updateAdminStatus(adminStat: Boolean){
        admin = adminStat
    }

    override fun onGameMessage(message: String) {
        Log.d("WS_IN", message)

        val data = gson.fromJson(message, GameCreatedMessage::class.java)

        if (data.gameID != null) {
            gameid = data.gameID
            onGameCreated?.invoke(gameid!!)
        }
    }

    //Game Session Functions
    fun createGame(onGameCreated: (String) -> Unit){
        this.onGameCreated = onGameCreated

        socket = GameWebSocketManager()

        //Mac Address Not Available at this time.
        socket?.startConnection(playerID)
        val json = gson.toJson(
            mapOf(
                "type" to "CREATE_GAME",
                "playerID" to playerID
            )
        )
        admin = true
        socket?.addListener(this)
        socket?.send(json)

    }

    fun joinGame(gameID: String?, macAddress: String){


        if(socket == null){
            socket = GameWebSocketManager()
            socket?.startConnection(playerID)
        }
        val json = gson.toJson(
            mapOf(
                "type" to "JOIN_GAME",
                "playerID" to playerID,
                "gameID" to gameID
            )
        )
        socket?.send(json)

    }

    fun startGame(){
        if(admin == true){
            if(socket == null){
                socket = GameWebSocketManager()
                socket?.startConnection(playerID)
                joinGame(gameid, playerID)
                val json = gson.toJson(
                    mapOf(
                        "type" to "START_GAME",
                        "playerID" to playerID,
                    )
                )

                socket?.send(json)
            } else{
                val json = gson.toJson(
                    mapOf(
                        "type" to "START_GAME",
                        "playerID" to playerID,
                    )
                )

                socket?.send(json)
            }
        }

    }

    fun endGame(){
        if (admin == true){
            if(socket == null){
                socket = GameWebSocketManager()
                socket?.startConnection(playerID)
                joinGame(gameid, playerID)
                val json = gson.toJson(
                    mapOf(
                        "type" to "END_GAME",
                        "gameID" to gameid,
                    )
                )

                socket?.send(json)
            } else{
                val json = gson.toJson(
                    mapOf(
                        "type" to "END_GAME",
                        "gameID" to gameid,
                    )
                )

                socket?.send(json)
            }

            //Exit The User HERE.

        }
    }

    fun leaveGame(){

    }

    //--------------------------
    fun sendLocation(){

    }




}