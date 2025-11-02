package ru.ac.uniyar.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.User

class UsersVM(val users: List<User>): ViewModel