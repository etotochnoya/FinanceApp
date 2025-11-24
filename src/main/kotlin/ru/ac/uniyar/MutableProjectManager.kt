package ru.ac.uniyar

import ru.ac.uniyar.domain.Projects

class MutableProjectManager(initial: Projects) {
    var projects: Projects = initial

    fun updateProjects(updateFn: (Projects) -> Projects) {
        this.projects = updateFn(this.projects)
    }
}