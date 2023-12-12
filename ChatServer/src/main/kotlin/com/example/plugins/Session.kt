package com.example.plugins

import com.example.session.UserSession
import io.ktor.server.application.*
import io.ktor.server.sessions.*

fun Application.configureSession() {
    install(Sessions) {
        cookie<UserSession>("USER_SESSION")
    }
}