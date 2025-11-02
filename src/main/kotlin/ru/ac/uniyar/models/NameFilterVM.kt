package ru.ac.uniyar.models

import org.http4k.template.ViewModel
import ru.ac.uniyar.domain.Project

class NameFilterVM(val projects: List<Project>, val name: String?) : ViewModel
