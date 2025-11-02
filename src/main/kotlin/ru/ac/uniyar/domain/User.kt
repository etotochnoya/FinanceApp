package ru.ac.uniyar.domain

import java.time.LocalDateTime

class User(
    val login: String,
    val password: String,
    val date: LocalDateTime? = LocalDateTime.now()
)