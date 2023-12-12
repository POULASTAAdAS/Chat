package com.example.plugins

import com.example.routes.login
import com.example.server.Server
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    routing {
        login()
    }
}
