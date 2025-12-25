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

    private fun filterProjectListByName(name: String?, projectList: List<Project>): List<Project>{
        if (projectList.isEmpty())
            return emptyList()
        return projectList.filter { project -> project.projectName == name }
    }
    fun projectNameFilter(name: String?): List<Project> {
        val filteredProjects = filterProjectListByName(name, projects)
        return filteredProjects
    }

    private fun filterProjectListByEntrepreneur(name: String?, projectList: List<Project>): List<Project>{
        if (projectList.isEmpty())
            return emptyList()
        return projectList.filter{ project -> project.entrepreneur == name }
    }
    fun entrepreneurFilter(name: String?): List<Project> {
        val filteredProjects = filterProjectListByEntrepreneur(name, projects)
        return filteredProjects.sortedBy { it.id }
    }

    private fun removeProjectFromList(id: Int, projectList: List<Project>): List<Project>{
        return projectList.filter { project -> project.id != id }
    }
    fun removeToId(id: Int): Projects{
        val newProjects = removeProjectFromList(id, projects)
        return Projects(newProjects)
    }

    private fun replaceProjectInList(project: Project, projectList: List<Project>): List<Project> {

        return projectList.map { currentProject ->
            if (currentProject.id == project.id) {
                project
            } else {
                currentProject
            }
        }
    }
    fun replaceToId(project: Project): Projects{
        val newProjects = replaceProjectInList(project, projects)
        return Projects(newProjects)
    }

    private fun findProjectById(id: Int, projectList: List<Project>): Project {
        val foundProject = projectList.find { project ->
            project.id == id
        }
        return foundProject ?: throw IllegalArgumentException("Project $id not found.")
    }
    fun takeToId(id: Int): Project {
        return findProjectById(id, projects)
    }
}
