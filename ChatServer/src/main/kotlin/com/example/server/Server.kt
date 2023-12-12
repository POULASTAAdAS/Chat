package com.example.server

import com.example.data.model.Chat
import com.example.data.model.User
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentLinkedQueue

class Server {
    val users = ConcurrentHashMap<String, ConcurrentLinkedQueue<Chat>>() // phoneNumber , List<Chat>


    fun addUSer(user: User) {
        users[user.phoneNumber] = ConcurrentLinkedQueue<Chat>()
//        user.startPinging()
    }
}