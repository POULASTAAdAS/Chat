package com.example.routes

import com.example.data.model.Endpoints
import com.example.data.model.LogInRequest
import com.example.server
import com.example.utils.Constants.PARAMETER_KEY_PHONE_NUMBER
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.login() {
    route(Endpoints.Login.route) {
        get {
            val logInRequest = call.receive<LogInRequest>()






            call.respond("test")
        }
    }
}