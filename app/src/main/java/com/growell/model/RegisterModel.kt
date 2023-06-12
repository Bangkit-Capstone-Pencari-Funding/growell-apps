package com.growell.model

data class RegisterRequest(val name: String, val phone: String, val email: String, val password: String)

data class RegisterResponse(val payload: PayloadRegister)

data class PayloadRegister(val token: String)