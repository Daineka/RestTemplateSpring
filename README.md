Инструкция по запуску
---
Создать базу данных, откорректировать resources/app.properties, заполнить бд используя файл resources/db-migration.sql.
Собрать проект в .war файл и запустить используя сервер Tomcat (либо настроить Tomcat в IntelliJ IDEA)

Endpoints
---
Authors:

~~~
вывод всех авторов:
GET http://localhost:8080/authors

вывод автора по id:
GET http://localhost:8080/authors/1

Вывод всех авторов с книгами авторов
GET http://localhost:8080/authors/withBooks

создание автора:
POST http://localhost:8080/authors
{
        "name":"Test Name"
}

обновление автора:
PUT http://localhost:8080/authors
{
        "id": 1,
        "name":"Updated Name"
}

удаление автора
DELETE http://localhost:8080/authors/1
~~~

Books:

~~~
вывод всех книг:
GET http://localhost:8080/books

вывод всех книг с автором книги и жанрами книги:
http://localhost:8080/books/withAuthorAndGenres

вывод книги по id:
GET http://localhost:8080/books/1

создание книги:
PUT http://localhost:8080/books
{
    "title": "Test Title",
    "publishedYear": 2012,
    "authorId": 1
}

обновление книги:
PUT http://localhost:8080/books
{
    "id": 1,
    "title": "Updated Title",
    "publishedYear": 2000,
    "authorId": 1
}

удаление книги
DELETE http://localhost:8080/books/1
~~~

Genres:

~~~
вывод всех жанров:
GET http://localhost:8080/genres

вывод жанра по id:
GET http://localhost:8080/genres/1

создание жанра:
POST http://localhost:8080/genres
{
    "name": "Test Genre"
}

обновление жанра:
PUT http://localhost:8080/genres
{
    "id":1,
    "name": "Updated Genre"
}

удаление заказа
DELETE http://localhost:8080/genres/1
~~~

Отчёт о покрытии тестами
---
![img.png](imgTestCoverage%2Fimg.png)