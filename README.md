Project Ninja
=============
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Bangerok_NinjaProject&metric=alert_status)](https://sonarcloud.io/dashboard?id=Bangerok_NinjaProject)
[![CheckStyle](https://github.com/Bangerok/NinjaProject/actions/workflows/check-style.yml/badge.svg?branch=master)](https://github.com/Bangerok/NinjaProject/actions/workflows/check-style.yml)
[![EsLint](https://github.com/Bangerok/NinjaProject/actions/workflows/eslint.yml/badge.svg?branch=master)](https://github.com/Bangerok/NinjaProject/actions/workflows/eslint.yml)
[![Build](https://github.com/Bangerok/NinjaProject/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Bangerok/NinjaProject/actions/workflows/build.yml)

### Description
___
Consists of two modules — the client part and the server part:
1. **[ninja-client](https://github.com/Bangerok/NinjaProject/tree/master/ninja-client)** is written in VueJS.
2. **[ninja-server](https://github.com/Bangerok/NinjaProject/tree/master/ninja-server)** is written using 
   **Spring Boot** and **Java 16**.

### Instructions for starting the project
___

1. Download sources or clone the project via git;
2. Deploying **[the database](https://github.com/Bangerok/NinjaProject/blob/master/docker/postgresql/postgres.bat)** 
   that the server module will work with.
3. Run client module 
   (**[instructions](https://github.com/Bangerok/NinjaProject/tree/master/ninja-client#start-up-instructions)**);
4. Server module launch 
   (**[instructions](https://github.com/Bangerok/NinjaProject/tree/master/ninja-server#start-up-instructions)**);
5. Three applications will run on different ports: **NinjaClient — 3000 port**, **NinjaServer — 9000 port** and 
   **Database — 5024**. _Change ports if you want_.
   
_**P.S.: All commands are executed from the console. It is also convenient to use launch configurations, for example, in
Intellij Idea (IDE).**_

### License
___

**[MIT](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt)**

_Copyright ©2021, Vladislav (Bangerok) Kuznetsov_