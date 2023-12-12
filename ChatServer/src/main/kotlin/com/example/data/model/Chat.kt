package com.example.data.model

import java.util.concurrent.ConcurrentLinkedQueue

class Chat(
    val privateChat: User? = null,
    val groupChat: ConcurrentLinkedQueue<User>? = null
) {

}