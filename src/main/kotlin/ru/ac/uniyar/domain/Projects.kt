package ru.ac.uniyar.domain

import java.lang.IllegalArgumentException

class Projects(myProjects: List<Project>) {
    private val projects: List<Project> = myProjects.toList()

    private fun addLastToProjectList(project: Project, projectList: List<Project>): List<Project>{
        return projectList + project.copy()
    }

    fun addNewProject(project: Project): Projects{
        return Projects(addLastToProjectList(project, projects))
    }

    fun size() = projects.size

    fun getList(): List<Project> = projects.toList()

    private fun filterProjectListByName(name: String?, projectList: List<Project>): List<Project>{
        var filteredProjects: List<Project> = listOf()
        for (project in projects) {
            if (project.projectName == name) {
                filteredProjects = addLastToProjectList(project, filteredProjects)
            }
        }
        return filteredProjects
    }
    fun projectNameFilter(name: String?): List<Project> {
        val filteredProjects = filterProjectListByName(name, projects)
        return filteredProjects
    }

    private fun filterProjectListByEntrepreneur(name: String?, projectList: List<Project>): List<Project>{
        var filteredProjects: List<Project> = listOf()
        for (project in projects) {
            if (project.entrepreneur == name) {
                filteredProjects = addLastToProjectList(project, filteredProjects)
            }
        }
        return filteredProjects
    }
    fun entrepreneurFilter(name: String?): List<Project> {
        val filteredProjects = filterProjectListByEntrepreneur(name, projects)
        return filteredProjects.sortedBy { it.id }
    }

    private fun removeProjectFromList(id: Int, projectList: List<Project>): List<Project>{
        var newProjects: List<Project> = listOf()
        for(i in 0 until projects.size)
            if(projects[i].id != id){
                newProjects = addLastToProjectList(projects[i], newProjects)
            }
        return newProjects
    }
    fun removeToId(id: Int): Projects{
        val newProjects = removeProjectFromList(id, projects)
        return Projects(newProjects)
    }

    private fun replaceProjectInList(project: Project, projectList: List<Project>): List<Project>{
        var newProjects: List<Project> = listOf()
        for(i in 0 until projects.size)
            if(projects[i].id == project.id){
                newProjects = addLastToProjectList(project, newProjects)
            }
            else{
                newProjects = addLastToProjectList(projects[i], newProjects)
            }
        return newProjects
    }
    fun replaceToId(project: Project): Projects{
        val newProjects = replaceProjectInList(project, projects)
        return Projects(newProjects)
    }

    private fun findProjectById(id: Int, projectList: List<Project>): Project{
        for (project in projectList) {
            if (project.id == id) {
                return project
            }
        }
        throw IllegalArgumentException("Project $id not found.")
    }
    fun takeToId(id: Int): Project {
        return findProjectById(id, projects)
    }
}
