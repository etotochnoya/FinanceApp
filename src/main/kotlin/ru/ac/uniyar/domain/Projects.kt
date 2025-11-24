package ru.ac.uniyar.domain

import java.lang.IllegalArgumentException

class Projects(myProjects: List<Project>) {
    private val projects: List<Project> = myProjects.toList()
    fun addNewProject(project: Project): Projects{
        return Projects(projects + project)
    }

    fun size() = projects.size

    fun getList(): List<Project> = projects.toList()

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

    fun removeToId(id: Int): Projects{
        val newProjects = mutableListOf<Project>()

        for(i in 0 until projects.size)
            if(projects[i].id != id){
                newProjects.add(projects[i])
            }

        return Projects(newProjects)
    }

    fun replaceToId(project: Project): Projects{
        val newProjects = mutableListOf<Project>()

        for(i in 0 until projects.size)
            if(projects[i].id == project.id){
                newProjects.add(project)
            }
            else{
                newProjects.add(projects[i])
            }

        return Projects(newProjects)
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
