package com.example.data.model

sealed class Endpoints(val route: String) {
    data object Login : Endpoints(route = "/api/login")
    data object App : Endpoints(route = "ws/app")
}