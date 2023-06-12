package com.growell.model

data class LoginRequest(val name: String, val email: String, val password: String)

data class LoginResponse(val payload: Payload)

data class Payload(val token: String)