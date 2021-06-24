Module NinjaServer from NinjaProject
=============================

### Description
___

One of the main 2 modules: responsible for assembling the server side of the application and, in turn, so far consists
of 4 modules:
1. **NinjaDatabase**. Working with the database;
2. **NinjaCore**. The main core of the application;
3. **NinjaSecurity**. Application security;
4. **NinjaWeb**. Full RESTapi application.

### Start-up instructions
___

1. Deploying the database that the server module will work with:
    - There is a file at the root of this module
      — **[docker-compose.yml](https://github.com/Bangerok/NinjaProject/blob/master/docker/postgresql/postgres-compose.yml)**.
      It will be used to launch and configure the application database.
    - To start the database, you need to go to the root of the server module. After that, you need to run the 
      **postgres.bat** file.
    - For the docker to work, you need to install it.
      _[Classic installation option for Linux, Windows and other systems](https://docs.docker.com/engine/install/)_.
2. Further start of the server:
    - The server module uses 5 private settings for: google authorization, sending mail and generating JWT tokens. This
      data should not be open for public use, so it is hidden in the project. Here is a list of these private settings:
        1. **“spring.security.oauth2.client.registration.google.clientId”**,
        2. **“spring.security.oauth2.client.registration.google.clientSecret”**,
        3. **“app.auth.tokenSecret”**,
        4. **“spring.mail.username”**,
        5. **“spring.mail.password”**.
    - One way to start this is to run the command _**”mvn spring-boot: run” (including changing the list of settings
      from the previous point)**_ from the root of the NinjaServer module.

_**P.S.: All commands are executed from the console. It is also convenient to use launch configurations, for example, in
Intellij Idea (IDE).**_

### Includes functionality
___

- Setting up, connecting and working with the **Postgres database**.
- **Using LiquiBase** to create a database structure and initially populate it.
- **Localization** of messages for languages: English and Russian.;
- Configuring the use of **Thymeleaf templates**;
- Application security using **JWT authorization tokens**;
- **OAuth2** authorization/registration user.

### Technology stack
___

- Spring Boot: WEB, Data, Mail, Security, OAuth2 (client & jose);
- Postgres;
- LiquiBase;
- Lombok;
- Docker;
- Other.