package ru.ac.uniyar.domain

class Users(val myUsers: List<User>) {
    val users = myUsers.toMutableList()
}