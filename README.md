Project Ninja
=============================

### Description
___

Consists of two modules — the client part (NinjaClient), and the server part (NinjaServer).

1. The first module is written in VueJS, the dependencies of which can be viewed in the file
   — **[package.json](https://github.com/Bangerok/NinjaProject/blob/master/NinjaClient/package.json)**
   . _At the moment, the functionality is working_:
    - regular authorization via email/password and authorization via Google;
    - registration with email confirmation via a verification token (it is possible to re-request a
      verification token);
    - there are three pages so far without content;
    - interface customization — language, dark/light theme settings;
2. The second module is written using **spring-boot-2.5.0-snapshot**, the dependencies of which can
   be viewed in the file
   — **[pom.xml](https://github.com/Bangerok/NinjaProject/blob/master/NinjaServer/pom.xml)**. _At
   the moment, the functionality is working_:
    - standard application launch via spring-boot;
    - spring-security oAuth2;
    - JWT;
    - sending messages with confirmation of registration to e-mail. A template written in thymeleaf
      is used;
    - database versioning using liquibase;
    - localization of the message (english and russian);
    - use for handling errors from controllers — **ControllerAdvice**;
    - validation of data received from the server through custom annotations;
    - and much more.

### Instructions for starting the project
___

1. Download sources or clone the project via git.
2. Run client
   module (**[NinjaClient](https://github.com/Bangerok/NinjaProject/tree/master/NinjaClient)**):
    - First you need to download and install all dependencies with the _**“npm install”**_ command.
    - After the dependencies are fully downloaded and installed, you can run the client module. To
      do this, run the _**“npm run serve”**_ command at the root of the module.
3. Deploying the database that the server module will work with:
    - There is a file at the root of this module
      — **[docker-compose.yml](https://github.com/Bangerok/NinjaProject/blob/master/NinjaServer/docker-compose.yml)**.
      It will be used to launch and configure the application database.
    - To start the database, you need to go to the root of the server module. After that, you need
      to run the command _**“docker-compose up”**_.
    - For the docker to work, you need to install
      it. _[Classic installation option for Linux, Windows and other systems](https://docs.docker.com/engine/install/)_.
4. Server module
   launch (**[NinjaServer](https://github.com/Bangerok/NinjaProject/tree/master/NinjaClient)**):
    - The server module uses 6 private settings for: google authorization, sending mail and
      generating JWT tokens. This data should not be open for public use, so it is hidden in the
      project. Those that remained in the commit history are changed/regenerated and are no longer
      relevant. Here is a list of these private settings:
        1. **“spring.security.oauth2.client.registration.google.clientId”**,
        2. **“spring.security.oauth2.client.registration.google.clientSecret”**,
        3. **“app.auth.tokenSecret”**,
        4. **“app.auth.tokenExpirationMsec”**,
        5. **“spring.mail.username”**,
        6. **“spring.mail.password”**.
    - One way to start this is to run the command _**"mvn spring-boot: run" (including changing the
      list of settings from the previous point)**_ from the root of the server module.
5. Three applications will run on different ports: **NinjaClient — 3000 port**, **NinjaServer — 9000
   port** and **Database — 5024**. _Change ports if you want_.

_**P.S.: All commands are executed from the console. It is also convenient to use launch
configurations, for example, in Intellij Idea (IDE).**_

### License
___

**[MIT](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt)**

_Copyright ©2021, Vladislav (Bangerok) Kuznetsov_