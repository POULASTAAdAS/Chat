package com.example.data.model

import com.example.gson
import com.example.utils.Constants.PING_FREQUENCY
import io.ktor.websocket.*
import kotlinx.coroutines.*

data class User(
    val phoneNumber: String,
    val userName: String,
    val profilePhoto: String? = null,
    var socket: WebSocketSession
) {
    private var pingJob: Job? = null

    private var pingTime = 0L
    private var pongTime = 0L

    var isOnline = true

    @OptIn(DelicateCoroutinesApi::class)
    fun startPinging() {
        pingJob?.cancel()
        pingJob = GlobalScope.launch {
            while (true) {
                sendPing()
                delay(PING_FREQUENCY)
            }
        }
    }

    private suspend fun sendPing() {
        pingTime = System.currentTimeMillis()

        socket.send(Frame.Text(gson.toJson(Ping())))
        delay(PING_FREQUENCY)
        if (pingTime - pongTime > PING_FREQUENCY) {
            isOnline = false
            // todo user left
            pingJob?.cancel()
        }
    }

    fun receivedPong() {
        pongTime = System.currentTimeMillis()
        isOnline = true
    }

    fun disConnect() {
        pingJob?.cancel()
    }
}