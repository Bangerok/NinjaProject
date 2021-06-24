Project Ninja
=============================

### Description
___

Consists of two modules — the client part and the server part:
1. **NinjaClient** is written in VueJS, the dependencies of which can be viewed in the file
   — **[package.json](https://github.com/Bangerok/NinjaProject/blob/master/NinjaClient/package.json)**.
2. **NinjaServer** is written using **Spring Boot**, the dependencies of which can be viewed in the file
   — **[pom.xml](https://github.com/Bangerok/NinjaProject/blob/master/NinjaServer/pom.xml)**.

### Instructions for starting the project
___

1. Download sources or clone the project via git;
2. Deploying **[the database](https://github.com/Bangerok/NinjaProject/blob/master/docker/postgresql/postgres-compose.yml)** 
   that the server module will work with.
3. Run client module (**[instructions](https://github.com/Bangerok/NinjaProject/tree/master/NinjaClient)**);
4. Server module launch (**[instructions](https://github.com/Bangerok/NinjaProject/tree/master/NinjaServer)**);
5. Three applications will run on different ports: **NinjaClient — 3000 port**, **NinjaServer — 9000 port** and 
   **Database — 5024**. _Change ports if you want_.
   
_**P.S.: All commands are executed from the console. It is also convenient to use launch configurations, for example, in
Intellij Idea (IDE).**_

### License
___

**[MIT](https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt)**

_Copyright ©2021, Vladislav (Bangerok) Kuznetsov_