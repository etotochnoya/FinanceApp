package ru.ac.uniyar.domain

import java.lang.IllegalArgumentException

class Projects(myProjects: List<Project>) {
    private val projects: List<Project> = myProjects.toList()

    private fun addLastToProjectList(project: Project, projectList: List<Project>): List<Project>{
        return projectList + project
    }

    fun addNewProject(project: Project): Projects{
        return Projects(addLastToProjectList(project, projects))
    }

    fun size() = projects.size

    fun getList(): List<Project> = projects.toList()

    fun projectNameFilter(name: String?): List<Project> {
        var filteredProjects: List<Project> = listOf()

        for (project in projects) {
            if (project.projectName == name) {
                filteredProjects = addLastToProjectList(project, filteredProjects)
            }
        }

        return filteredProjects.sortedBy { it.id }
    }

    fun entrepreneurFilter(name: String?): List<Project> {
        var filteredProjects: List<Project> = listOf()

        for (project in projects) {
            if (project.entrepreneur == name) {
                filteredProjects = addLastToProjectList(project, filteredProjects)
            }
        }

        return filteredProjects.sortedBy { it.id }
    }

    fun removeToId(id: Int): Projects{
        var newProjects: List<Project> = listOf()

        for(i in 0 until projects.size)
            if(projects[i].id != id){
                newProjects = addLastToProjectList(projects[i], newProjects)
            }

        return Projects(newProjects)
    }

    fun replaceToId(project: Project): Projects{
        var newProjects: List<Project> = listOf()

        for(i in 0 until projects.size)
            if(projects[i].id == project.id){
                newProjects = addLastToProjectList(project, newProjects)
            }
            else{
                newProjects = addLastToProjectList(projects[i], newProjects)
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
