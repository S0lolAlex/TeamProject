# __NOTE IT__

__Note IT__ is an online notebook.
It is a simple and convenient solution for storing important information and current tasks without fear of losing something.
The program allows you to create new notes, edit or delete existing ones.


## Buid With

* <img src="https://miro.medium.com/v2/resize:fit:1400/format:webp/1*98O4Gb5HLSlmdUkKg1DP1Q.png" alt="Spring Boot" width="200" height="100">

  * Spring Boot Core
  * Spring Boot MVC
  * Spring Boot Date
  * Spring Boot Security
* <img src="https://upload.wikimedia.org/wikipedia/commons/a/a1/H2_logo.png" alt="H2" width="200" height="100">

* <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/800px-Postgresql_elephant.svg.png" alt="Postgresql" width="200" height="200">

* <img src="https://upload.wikimedia.org/wikipedia/commons/f/f3/Flyway-logo-tm.png" alt="Flyway" width="200" height="200">


## Getting Started

```
git clone ...

mvn clean package 

java -jar "path on jar file"

```
Application have two profile, its `default` and `prod`

by default the application will run on the H2 database

If you want to use `prod` profile . Before running the program, you need to specify the database changes to the database.

For Windows:
```
set DB_HOST=...
set DB_PORT=...
set DB_NAME=...
set DB_USERNAME=...
set DB_PASSWORD=...
java -jar "Path to jar file" --spring.profiles.active=prod

```

## Usage 

Each user has the ability to create multiple notes, edit them, and delete them.
You must first register

When creating a user, there are certain requirements for a login and password.
They are written in the User class.
All controllers, services, and so on are registered in the User directory


```java
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    @Size(min = 5, max = 50, message = "Size must be between 5 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Login can contain only numbers and latin symbols")
    private String login;

    @Column
    @Size(min = 8, max = 100, message = "Size must be between 8 and 100 characters")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Note> notes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;
}

```

There are also limitations when creating a notebook entry
they are spelled out in the Note class.
All controllers, services, and so on are registered in the Note directory

```java
@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @Size(min = 5, max = 100, message = "Size must be between 5 and 100 characters")
    private String title;

    @Column
    @Size(min = 5, max = 10000, message = "Size must be between 5 and 10000 characters")
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private Privacy privacy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getAuthor() {
        return user == null ? "User not found" : user.getLogin();
    }
}
```
## User pages 

Page layout is defined in the Templates directory
examples below 

#### Registration

![Рисунок1](https://github.com/EugeneDnipro/DevTeamProject_Notes/assets/117183679/95e06e9c-b1bf-409b-9ae0-ad00e8c380b0)

#### Login
![Рисунок1](https://github.com/EugeneDnipro/DevTeamProject_Notes/assets/117183679/19d9862a-6330-4b0a-b263-608302ed2927)

#### Create Note 
![Рисунок2](https://github.com/EugeneDnipro/DevTeamProject_Notes/assets/117183679/4385ee4e-4f30-40d8-bdd3-4278fcc9856f)

#### Delete, Update, Share
![Рисунок3](https://github.com/EugeneDnipro/DevTeamProject_Notes/assets/117183679/4f967800-8d8b-4769-81c6-f1d900765d88)

## About our team 

![Рисунок4](https://github.com/EugeneDnipro/DevTeamProject_Notes/assets/117183679/679f0512-0cf7-4c69-bab3-bb896d2fc743)
