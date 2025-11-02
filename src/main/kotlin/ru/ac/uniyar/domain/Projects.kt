package ru.ac.uniyar.domain

import java.lang.IllegalArgumentException

class Projects(myProjects: List<Project>) {
    private val projects = myProjects.toMutableList()
    var counterProjects = projects.size

    fun add(project: Project) {
        projects.add(project)
        counterProjects++
    }

    fun size() = projects.size

    fun getList(): List<Project> = projects

    fun projectNameFilter(name: String?): List<Project> {
        val filteredProjects = mutableListOf<Project>()

        for (project in projects) {
            if (project.projectName == name) {
                filteredProjects.add(project)
            }
        }

        return filteredProjects.sortedBy { it.id }
    }

    fun entrepreneurFilter(name: String?): List<Project> {
        val filteredProjects = mutableListOf<Project>()
        for (project in projects) {
            if (project.entrepreneur == name) {
                filteredProjects.add(project)
            }
        }

        return filteredProjects.sortedBy { it.id }
    }

    fun removeToId(id: Int){
        for(i in 0 until projects.size)
            if(projects[i].id == id){
                projects.removeAt(i)
                return
            }
    }

    fun replaceToId(project: Project){
        for(i in 0 until projects.size)
            if(projects[i].id == project.id){
                projects[i] = project
                return
            }
    }

    fun takeToId(id: Int): Project {
        val tmpProjects = projects

        for (project in tmpProjects) {
            if (project.id == id) {
                return project
            }
        }

        throw IllegalArgumentException("Project $id not found.")
    }
}
