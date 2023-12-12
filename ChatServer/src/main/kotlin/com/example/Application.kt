package com.example

import com.example.data.model.LogInRequest
import com.example.plugins.*
import com.example.server.Server
import com.example.session.UserSession
import com.example.utils.Constants.PARAMETER_KEY_PHONE_NUMBER
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.application.ApplicationCallPipeline.ApplicationPhase.Plugins
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

val server = Server()
val gson = Gson()

fun Application.module() {
    configureSockets()
    configureSerialization()
    configureSession()
    configureMonitoring()
    configureRouting()

    intercept(Plugins) {
        if (call.sessions.get<UserSession>() == null) {
            val phoneNumber = call.receive<LogInRequest>().phoneNumber

            try {
                // todo privacy check ex: verify if the phone number belongs to the person who is sending req
                val isNotVerified = false

                if (isNotVerified) throw PhoneNumberNotVerifiedException(message = "phone number is not Verified")
                if (phoneNumber.length != 10) throw PhoneNumberNotValid("phone number is not valid")

                phoneNumber.toLong()
            } catch (e: PhoneNumberNotVerifiedException) {
                call.respond(
                    message = e.message,
                    status = HttpStatusCode.Forbidden,
                )

                return@intercept
            } catch (e: PhoneNumberNotValid) {
                call.respond(
                    message = e.message,
                    status = HttpStatusCode.Forbidden,
                )

                return@intercept
            } catch (e: NumberFormatException) {
                call.respond(
                    message = "phone number can't contain char",
                    status = HttpStatusCode.BadRequest,
                )

                return@intercept
            }


            call.sessions.set(
                UserSession(
                    phoneNumber = phoneNumber,
                    sessionId = generateSessionId()
                )
            )
        }
    }
}

private class PhoneNumberNotVerifiedException(override val message: String) : Exception(message)
private class PhoneNumberNotValid(override val message: String) : Exception(message)