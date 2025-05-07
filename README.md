Student & Course Management System
This is a simple backend application built using Java and Spring Boot to manage students and their courses. It supports full CRUD operations and models the relationship between students and courses (Many-to-Many).
--------------------------------------------------------

✨ Features:

🎓 Student Management :
Create, view, update, and delete students.

Each student has:

First name

Last name

Email

Each student can enroll in multiple courses.

--------------------------------------------------------


📚 Course Management :
Create, view, update, and delete courses.

Each course has:

Name

Total Hours

Each course can have multiple students enrolled.
--------------------------------------------------------


🛠️ Enrollment Management :

Enroll or unenroll students in/from courses.

Validate enrollment (prevent duplicate enroll or unenroll if not enrolled).

--------------------------------------------------------


🏗️ Clean architecture with:

Entities

DTOs (Data Transfer Objects)

Repositories (Spring Data JPA)

Service layer

Controller layer


--------------------------------------------------------

💡 Technologies Used:

Java 17

Spring Boot

Spring Data JPA

PostgreSQL

Lombok

