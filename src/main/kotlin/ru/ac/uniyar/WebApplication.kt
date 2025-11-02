package ru.ac.uniyar

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.core.Status.Companion.FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.body.form
import org.http4k.core.findSingle
import org.http4k.core.queries
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.ResourceLoader
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.routing.static
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.template.PebbleTemplates
import org.http4k.template.TemplateRenderer
import ru.ac.uniyar.domain.Project
import ru.ac.uniyar.domain.Projects
import ru.ac.uniyar.domain.User
import ru.ac.uniyar.domain.Users
import ru.ac.uniyar.models.*
import java.time.LocalDateTime

fun showMainPage(renderer: TemplateRenderer): HttpHandler = {
    Response(Status.OK).body(renderer(MainPageVM()))
}

fun showProjectsPage(renderer: TemplateRenderer, projects: Projects): HttpHandler = {
    Response(Status.OK).body(renderer(ProjectsVM(projects.getList())))
}

fun showProject(renderer: TemplateRenderer, projectsList: List<Project>): HttpHandler = { request ->
    val id = request.path("id").toString()
    val projects = Projects(projectsList)
    val project = projects.takeToId(id.toInt())
    Response(OK).body(renderer(ProjectVM(project)))
}

fun showNewProject(renderer: TemplateRenderer, id: Int): HttpHandler = {
    Response(Status.OK).body(renderer(NewProjectVM(id)))
}

fun showProjectsWithNameFilter(renderer: TemplateRenderer, projects: Projects): HttpHandler = {
    val uri = it.uri
    val parametrs = uri.queries()
    val name = parametrs.findSingle("name")
    val myProjects = projects.projectNameFilter(name)
    Response(Status.OK).body(renderer(NameFilterVM(myProjects, name)))
}

fun showProjectsWithEnterpreneurFilter(renderer: TemplateRenderer, projects: Projects): HttpHandler = {
    val uri = it.uri
    val parametrs = uri.queries()
    val name = parametrs.findSingle("name")
    val myProjects = projects.entrepreneurFilter(name)
    Response(Status.OK).body(renderer(EnterpreneurFilterVM(myProjects, name)))
}

fun createNewProject(projects: Projects): HttpHandler = {
    val form = it.form()
    val entrepreneur = form.findSingle("entrepreneur")
    val projectName = form.findSingle("projectName")
    val description = form.findSingle("description")
    val targetFundSize = form.findSingle("targetFundSize")
    val beginYear = form.findSingle("beginYear")
    val beginMonth = form.findSingle("beginMonth")
    val beginDay = form.findSingle("beginDay")
    val endYear = form.findSingle("endYear")
    val endMonth = form.findSingle("endMonth")
    val endDay = form.findSingle("endDay")
    projects.add(
        Project(
            projects.counterProjects,
            projectName.toString(),
            entrepreneur.toString(),
            description.toString(),
            targetFundSize!!.toLong(),

            LocalDateTime.of(beginYear!!.toInt(), beginMonth!!.toInt(), beginDay!!.toInt(), 0, 0, 0),
            LocalDateTime.of(endYear!!.toInt(), endMonth!!.toInt(), endDay!!.toInt(), 0, 0, 0),
        ),
    )
    Response(Status.FOUND).header("Location", "/project/" + (projects.counterProjects - 1).toString())
}

fun showEditPage(renderer: TemplateRenderer, projects: Projects): HttpHandler = {
    val id = it.path("id")!!.toInt()
    val project = projects.takeToId(id)
    val projectEditVM = ProjectEditVM(project)
    Response(OK).body(renderer(projectEditVM))
}

fun editProject(projects: Projects): HttpHandler = {
    val id = it.path("id")!!.toInt()
    val project = projects.takeToId(id)
    val form = it.form()
    val startDate = LocalDateTime.of(
        form.findSingle("beginYear")!!.toInt(),
        form.findSingle("beginMonth")!!.toInt(),
        1, 1, 1 ,1
    )
    val endDate = LocalDateTime.of(
        form.findSingle("endYear")!!.toInt(),
        form.findSingle("endMonth")!!.toInt(),
        1, 1, 1 ,1
    )

    val otherProject = Project(
        project.id,
        form.findSingle("projectName")!!.toString(),
        form.findSingle("entrepreneur")!!.toString(),
        form.findSingle("description")!!.toString(),
        form.findSingle("targetFundSize")!!.toLong(),
        startDate,
        endDate,
    )
    projects.replaceToId(otherProject)
    //projects.add(editProject)
    Response(FOUND).header("Location", "/project/" + id.toString())
}

fun showUsersPage(renderer: TemplateRenderer, users: Users): HttpHandler = {
    Response(OK).body(renderer(UsersVM(users.users)))
}

fun showNewUser(renderer: TemplateRenderer): HttpHandler = {
    Response(Status.OK).body(renderer(NewUserVM()))
}

fun createNewUser(renderer: TemplateRenderer, users: Users): HttpHandler = {
    val form = it.form()
    val login = form.findSingle("login")!!.toString()
    val password = form.findSingle("password")!!.toString()
    users.users.add(User(login, password))

    Response(Status.FOUND).header("Location", "/users")
}

fun router(renderer: TemplateRenderer, projects: Projects, users: Users): HttpHandler = routes(
    "/" bind GET to showMainPage(renderer),
    "/project" bind GET to showProjectsPage(renderer, projects),

    "/users" bind GET to showUsersPage(renderer, users),
    "/users/new" bind GET to showNewUser(renderer),
    "/users/new" bind Method.POST to createNewUser(renderer, users),

    "/new" bind GET to showNewProject(renderer, projects.counterProjects),
    "/new" bind Method.POST to createNewProject(projects),
    "/project/{id}" bind GET to showProject(renderer, projects.getList()),
    "/project/{id}/edit" bind GET to showEditPage(renderer, projects),
    "project/{id}/edit" bind Method.POST to editProject(projects),

    "/nameFilter" bind GET to showProjectsWithNameFilter(renderer, projects),
    "/entrepreneurFilter" bind GET to showProjectsWithEnterpreneurFilter(renderer, projects),
    static(ResourceLoader.Classpath("/ru/ac/uniyar/public/")),
)

fun fillData(): Projects {
    // Инициализируем Projects с пустым списком, счетчик будет 0
    val projects = Projects(listOf())

    // --- Исходные проекты ---
    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ }, // Передаем текущее значение и инкрементируем счетчик
            "Онлайн сервис такси",
            "Райан Гослинг",
            "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
            1000000,
            LocalDateTime.of(2011, 9, 16, 0, 0, 0),
            LocalDateTime.of(2011, 11, 3, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ }, // Инкрементируем счетчик
            "Печать визиток",
            "Патрик Бэйтмен",
            "Быстрая и качественная печать визиток по вашему макету",
            300000,
            LocalDateTime.of(2000, 1, 21, 0, 0, 0),
            LocalDateTime.of(2000, 4, 14, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Разработка мобильного приложения",
            "Виталий Кукурузов",
            "Создание интуитивно понятного и функционального мобильного приложения для iOS и Android",
            1500000,
            LocalDateTime.of(2023, 3, 10, 0, 0, 0),
            LocalDateTime.of(2023, 10, 20, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Создание онлайн-курсов",
            "Сергей Огурцов",
            "Разработка образовательных курсов по популярным темам с экспертными преподавателями",
            500000,
            LocalDateTime.of(2022, 8, 1, 0, 0, 0),
            LocalDateTime.of(2023, 5, 15, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Автоматизация вывоза мусора",
            "Тони Сопрано",
            "Автоматизация вывоза мусора",
            750000,
            LocalDateTime.of(2023, 1, 5, 0, 0, 0),
            LocalDateTime.of(2023, 4, 25, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "3D-печать архитектурных моделей",
            "Оскар Айзек",
            "Создание детализированных 3D-моделей зданий и сооружений для презентаций",
            400000,
            LocalDateTime.of(2022, 11, 1, 0, 0, 0),
            LocalDateTime.of(2023, 2, 10, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Разработка игры-головоломки",
            "Флоренс Пью",
            "Создание увлекательной мобильной игры-головоломки с уникальным геймплеем",
            1200000,
            LocalDateTime.of(2023, 2, 15, 0, 0, 0),
            LocalDateTime.of(2023, 9, 30, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Автоматизация складских процессов",
            "Пол Мескал",
            "Внедрение системы автоматизации для управления складскими операциями и оптимизации логистики",
            2000000,
            LocalDateTime.of(2022, 6, 1, 0, 0, 0),
            LocalDateTime.of(2023, 1, 20, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Разработка CRM-системы",
            "Аня Тейлор-Джой",
            "Создание кастомизированной CRM-системы для управления клиентами и продажами",
            1800000,
            LocalDateTime.of(2023, 4, 1, 0, 0, 0),
            LocalDateTime.of(2023, 11, 1, 0, 0, 0),
        ),
    )

    projects.add(
        Project(
            projects.counterProjects.also { projects.counterProjects++ },
            "Оптимизация SEO для интернет-магазина",
            "Джереми Аллен Уайт",
            "Улучшение видимости интернет-магазина в поисковых системах для увеличения трафика и продаж",
            600000,
            LocalDateTime.of(2023, 5, 10, 0, 0, 0),
            LocalDateTime.of(2023, 8, 30, 0, 0, 0),
        ),
    )

    return projects
}

fun main() {
    val projects = fillData()
    val users = Users(listOf(User("admin", "123")))
    val renderer = PebbleTemplates().HotReload("src/main/resources")
    val app = router(renderer, projects, users)
    val server = app.asServer(Jetty(9000)).start()
    println("Server started on " + server.port())
}
