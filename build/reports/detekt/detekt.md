# detekt

## Metrics

* 54 number of properties

* 24 number of functions

* 17 number of classes

* 5 number of packages

* 19 number of kt files

## Complexity Report

* 389 lines of code (loc)

* 304 source lines of code (sloc)

* 195 logical lines of code (lloc)

* 3 comment lines of code (cloc)

* 29 cyclomatic complexity (mcc)

* 6 cognitive complexity

* 33 number of total code smells

* 0% comment source ratio

* 148 mcc per 1,000 lloc

* 169 code smells per 1,000 lloc

## Findings (33)

### complexity, LongParameterList (1)

The more parameters a function has the more complex it is. Long parameter lists are often used to control complex algorithms and violate the Single Responsibility Principle. Prefer functions with short parameter lists.

[Documentation](https://detekt.dev/docs/rules/complexity#longparameterlist)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Project.kt:5:14
```
The constructor(id: Int, projectName: String, entrepreneur: String, description: String, targetFundSize: Long, startDate: LocalDateTime, endDate: LocalDateTime, addTime: LocalDateTime) has too many parameters. The current threshold is set to 7.
```
```kotlin
2 
3 import java.time.LocalDateTime
4 
5 class Project(
!              ^ error
6     val id: Int,
7     val projectName: String,
8     val entrepreneur: String,

```

### complexity, TooManyFunctions (1)

Too many functions inside a/an file/class/object/interface always indicate a violation of the single responsibility principle. Maybe the file/class/object/interface wants to manage too many things at once. Extract functionality which clearly belongs together.

[Documentation](https://detekt.dev/docs/rules/complexity#toomanyfunctions)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:1:1
```
File 'D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\WebApplication.kt' with '15' functions detected. Defined threshold inside files is set to '11'
```
```kotlin
1 package ru.ac.uniyar
! ^ error
2 
3 import org.http4k.core.HttpHandler
4 import org.http4k.core.Method

```

### empty-blocks, EmptyDefaultConstructor (2)

Empty block of code detected. As they serve no purpose they should be removed.

[Documentation](https://detekt.dev/docs/rules/empty-blocks#emptydefaultconstructor)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/MainPageVM.kt:5:17
```
An empty default constructor can be removed.
```
```kotlin
2 
3 import org.http4k.template.ViewModel
4 
5 class MainPageVM() : ViewModel
!                 ^ error
6 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/NewUserVM.kt:5:16
```
An empty default constructor can be removed.
```
```kotlin
2 
3 import org.http4k.template.ViewModel
4 
5 class NewUserVM(): ViewModel
!                ^ error

```

### exceptions, ThrowingExceptionsWithoutMessageOrCause (1)

A call to the default constructor of an exception was detected. Instead one of the constructor overloads should be called. This allows to provide more meaningful exceptions.

[Documentation](https://detekt.dev/docs/rules/exceptions#throwingexceptionswithoutmessageorcause)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Projects.kt:42:81
```
A call to the default constructor of an exception was detected. Instead one of the constructor overloads should be called. This allows to provide more meaningful exceptions.
```
```kotlin
39 
40     fun takeToId(id: Int): Project {
41         val tmpProjects = projects
42         val project = tmpProjects.find { project -> project.id == id } ?: throw IllegalArgumentException()
!!                                                                                 ^ error
43         return project
44     }
45 }

```

### naming, MatchingDeclarationName (1)

If a source file contains only a single non-private top-level class or object, the file name should reflect the case-sensitive name plus the .kt extension.

[Documentation](https://detekt.dev/docs/rules/naming#matchingdeclarationname)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/formats/MultipartExample.kt:9:12
```
The file name 'MultipartExample' does not match the name of the single top-level declaration 'Name'.
```
```kotlin
6  import org.http4k.lens.Validator
7  import org.http4k.lens.multipartForm
8  
9  data class Name(val value: String)
!             ^ error
10 
11 // define fields using the standard lens syntax
12 val nameField = MultipartFormField.string().map(::Name, Name::value).required("name")

```

### naming, MemberNameEqualsClassName (3)

A member should not be given the same name as its parent class or object.

[Documentation](https://detekt.dev/docs/rules/naming#membernameequalsclassname)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Projects.kt:6:5
```
A member is named after the class. This might result in confusion. Either rename the member or change it to a constructor.
```
```kotlin
3  import java.lang.IllegalArgumentException
4  
5  class Projects(myProjects: List<Project>) {
6      private val projects = myProjects.toMutableList()
!      ^ error
7      var counterProjects = projects.size
8  
9      fun add(project: Project) {

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Users.kt:4:5
```
A member is named after the class. This might result in confusion. Either rename the member or change it to a constructor.
```
```kotlin
1 package ru.ac.uniyar.domain
2 
3 class Users(val myUsers: List<User>) {
4     val users = myUsers.toMutableList()
!     ^ error
5 }

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/storage/Salt.kt:4:5
```
A member is named after the class. This might result in confusion. Either rename the member or change it to a constructor.
```
```kotlin
1 package ru.ac.uniyar.storage
2 
3 class Salt {
4     val salt = "pSMoOb69C!lnDe@-6wM=49W9?GbLUUxlz2)m8dsXB/1*.gtpOJcDyA-jgvk*)+:e?vKa0OU(V?WsW5xAKQI6YCWpizh2gQX.z+bp"
!     ^ error
5 }

```

### style, MagicNumber (14)

Report magic numbers. Magic number is a numeric literal that is not defined as a constant and hence it's unclear what the purpose of this number is. It's better to declare such numbers as constants and give them a proper name. By default, -1, 0, 1, and 2 are not considered to be magic numbers.

[Documentation](https://detekt.dev/docs/rules/style#magicnumber)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:173:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
170             "Онлайн сервис такси",
171             "Райан Гослинг",
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
!!!             ^ error
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
176         ),

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:174:30
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
171             "Райан Гослинг",
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
!!!                              ^ error
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
176         ),
177     )

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:174:36
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
171             "Райан Гослинг",
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
!!!                                    ^ error
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
176         ),
177     )

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:174:39
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
171             "Райан Гослинг",
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
!!!                                       ^ error
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
176         ),
177     )

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:175:30
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
!!!                              ^ error
176         ),
177     )
178 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:175:36
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
!!!                                    ^ error
176         ),
177     )
178 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:175:40
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
172             "Закажи такси онлайн или по телефону и по низкой цене по всей стране",
173             1000000,
174             LocalDateTime.of(2011, 9, 16, 0, 0, 0),
175             LocalDateTime.of(2011, 11, 3, 0, 0, 0),
!!!                                        ^ error
176         ),
177     )
178 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:185:13
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
182             "Печать визиток",
183             "Патрик Бэйтмен",
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
!!!             ^ error
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
188         ),

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:186:30
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
183             "Патрик Бэйтмен",
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
!!!                              ^ error
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
188         ),
189     )

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:186:39
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
183             "Патрик Бэйтмен",
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
!!!                                       ^ error
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
188         ),
189     )

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:187:30
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
!!!                              ^ error
188         ),
189     )
190 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:187:36
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
!!!                                    ^ error
188         ),
189     )
190 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:187:39
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
184             "Быстрая и качественная печать визиток по вашему макету",
185             300000,
186             LocalDateTime.of(2000, 1, 21, 0, 0, 0),
187             LocalDateTime.of(2000, 4, 14, 0, 0, 0),
!!!                                       ^ error
188         ),
189     )
190 

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:199:37
```
This expression contains a magic number. Consider defining it to a well named constant.
```
```kotlin
196     val users = Users(listOf(User("admin", "123")))
197     val renderer = PebbleTemplates().HotReload("src/main/resources")
198     val app = router(renderer, projects, users)
199     val server = app.asServer(Jetty(9000)).start()
!!!                                     ^ error
200     println("Server started on " + server.port())
201 }
202 

```

### style, NewLineAtEndOfFile (7)

Checks whether files end with a line separator.

[Documentation](https://detekt.dev/docs/rules/style#newlineatendoffile)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Project.kt:16:2
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\domain\Project.kt is not ending with a new line.
```
```kotlin
13     val addTime: LocalDateTime = LocalDateTime.now(),
14 ){
15     val a = addTime.dayOfMonth
16 }
!!  ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/User.kt:9:2
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\domain\User.kt is not ending with a new line.
```
```kotlin
6      val login: String,
7      val password: String,
8      val date: LocalDateTime? = LocalDateTime.now()
9  )
!   ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/domain/Users.kt:5:2
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\domain\Users.kt is not ending with a new line.
```
```kotlin
2 
3 class Users(val myUsers: List<User>) {
4     val users = myUsers.toMutableList()
5 }
!  ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/NewUserVM.kt:5:29
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\models\NewUserVM.kt is not ending with a new line.
```
```kotlin
2 
3 import org.http4k.template.ViewModel
4 
5 class NewUserVM(): ViewModel
!                             ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/ProjectEditVM.kt:6:53
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\models\ProjectEditVM.kt is not ending with a new line.
```
```kotlin
3  import org.http4k.template.ViewModel
4  import ru.ac.uniyar.domain.Project
5  
6  class ProjectEditVM(val project: Project): ViewModel
!                                                      ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/UsersVM.kt:6:48
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\models\UsersVM.kt is not ending with a new line.
```
```kotlin
3  import org.http4k.template.ViewModel
4  import ru.ac.uniyar.domain.User
5  
6  class UsersVM(val users: List<User>): ViewModel
!                                                 ^ error

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/storage/Salt.kt:5:2
```
The file D:\Developer\WebApplication\src\main\kotlin\ru\ac\uniyar\storage\Salt.kt is not ending with a new line.
```
```kotlin
2 
3 class Salt {
4     val salt = "pSMoOb69C!lnDe@-6wM=49W9?GbLUUxlz2)m8dsXB/1*.gtpOJcDyA-jgvk*)+:e?vKa0OU(V?WsW5xAKQI6YCWpizh2gQX.z+bp"
5 }
!  ^ error

```

### style, UnusedPrivateMember (2)

Private member is unused and should be removed.

[Documentation](https://detekt.dev/docs/rules/style#unusedprivatemember)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:137:19
```
Function parameter `renderer` is unused.
```
```kotlin
134     Response(Status.OK).body(renderer(NewUserVM()))
135 }
136 
137 fun createNewUser(renderer: TemplateRenderer, users: Users): HttpHandler = {
!!!                   ^ error
138     val form = it.form()
139     val login = form.findSingle("login")!!.toString()
140     val password = form.findSingle("password")!!.toString()

```

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/models/NewProjectVM.kt:5:20
```
Private property `id` is unused.
```
```kotlin
2 
3 import org.http4k.template.ViewModel
4 
5 class NewProjectVM(id: Int) : ViewModel
!                    ^ error
6 

```

### style, WildcardImport (1)

Wildcard imports should be replaced with imports using fully qualified class names. Wildcard imports can lead to naming conflicts. A library update can introduce naming clashes with your classes which results in compilation errors.

[Documentation](https://detekt.dev/docs/rules/style#wildcardimport)

* D:/Developer/WebApplication/src/main/kotlin/ru/ac/uniyar/WebApplication.kt:28:1
```
ru.ac.uniyar.models.* is a wildcard import. Replace it with fully qualified imports.
```
```kotlin
25 import ru.ac.uniyar.domain.Projects
26 import ru.ac.uniyar.domain.User
27 import ru.ac.uniyar.domain.Users
28 import ru.ac.uniyar.models.*
!! ^ error
29 import java.time.LocalDateTime
30 
31 fun showMainPage(renderer: TemplateRenderer): HttpHandler = {

```

generated with [detekt version 1.22.0](https://detekt.dev/) on 2025-11-02 11:34:18 UTC
