<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
    <h1>
        <a href="https://ninjaenterprise.github.io/Ninja/">Ninja</a>
    </h1>
</div>

<div align="center">
    <a href="https://github.com/NinjaEnterprise/Ninja/blob/master/docs/translations/README_RU.md">
        <img alt="russian-version" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/languages/russian.png"/>
    </a>
</div>

<div align="center">
    <img src="https://img.shields.io/github/last-commit/NinjaEnterprise/Ninja" height="25" alt="last-commit" />
    <img src="https://tokei.rs/b1/github/NinjaEnterprise/Ninja?category=code" height="25" alt="code-lines" />
    <img src="https://sonarcloud.io/api/project_badges/measure?project=NinjaEnterprise_Ninja&metric=alert_status" height="25" alt="sonar-quality-gate" />
    <img src="https://github.com/NinjaEnterprise/Ninja/actions/workflows/check-style.yml/badge.svg" height="25" alt="checking-style" />
    <img src="https://github.com/NinjaEnterprise/Ninja/actions/workflows/sonar.yml/badge.svg" height="25" alt="checking-sonar" />
    <img src="https://github.com/NinjaEnterprise/Ninja/actions/workflows/build.yml/badge.svg" height="25" alt="build" />
</div>

### 📖 Description
___

Server part for connecting to the client interface. So far, only for training. Consists of 4 modules:
1. **[NinjaDatabase](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-database)**. Setting up and working 
   with the database. Includes such aspects:
   - **PostgreSQL**: data source configuration;
   - Configuring **LiquiBase**;
   - Executing scripts to create a database structure with some data;
   - Contains data models and repositories for working with them. 


2. **[NinjaCore](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-core)**. Basic services for working with 
   the application. Includes such aspects:
   - Configuring application **localization** for two languages: **english** and **russian**;
   - Configuring work with **sending messages to e-mail**. For email templates, **thymeleaf** is used;
   - Common services for interacting with the database. Example, to work with the model — **UserSetting**.


3. **[NinjaSecurity](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-security)**. Application security
   (**it is planned to separate the module into a separate microservice**). Includes such aspects:
   - Configuring **OAuth2** authorization;
   - Configuring **the JWT** to issue on authorization.


4. **[NinjaWeb](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-web)**. Provides **REST** interaction across 
   the application through controllers. Includes such aspects:
   - Interaction with the application through controllers. For example, for authorization;
   - Validation of data received from the client;
   - Launching Spring Boot application. 

### ❗ Requirements
___

* **JDK**: 16 and higher;
* **Docker**.

### 📋 Start-up instructions
___

*All commands are executed in the console. For all actions, you can use the IDE, but who knows about it — and
will do it, the instruction is not needed.*
<details style="margin-left: 40px">	
   <summary><b>Clone Repository</b></summary>
   <ol>
      <li>Create a folder: <code>mkdir GitProjects</code> (the folder name can be anything, but you will need to 
      continue to use only it);</li>
      <li>Go to the folder: <code>cd GitProjects</code>;</li>
      <li>Cloning a repository: <code>git clone https://github.com/NinjaEnterprise/Ninja.git</code>;</li>
      <li>Go to the folder: <code>cd Ninja</code>.</li>
      <li>Complete (<b>see: result below</b>).</li>
   </ol>

   <img alt="clone-repo" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/clone-repository.png"/>
</details>

<details style="margin-left: 40px">	
   <summary><b>Database Setup</b></summary>

   <i>After cloning the repository — we should already be in the project folder.</i>
   <ol>
      <li>For docker to work, you need to <b><a href="https://docs.docker.com/engine/install/">install</a></b> it first;</li>
      <li>To configure the database required for the application to work, go to the folder: <code>cd docker\postgresql</code>;</li>
      <li>Run the .bat file with the command: <code>postgres.bat</code> (<b>see: result below</b>). <b>Default port: 5024</b></li>
   </ol>

   <img alt="ninja-docker" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/ninja-docker.gif"/>
   <img alt="docker-info" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/docker-info.png"/>
</details>


<details style="margin-left: 40px">	
   <summary><b>Build Code</b></summary>
   <ol>
      <li>After starting docker and setting up the database, we go back to the root of the project: <code>cd ..\..</code>;</li>
      <li>We execute the command: <code>mvn clean install</code> (<b>see: result below</b>).</li>
   </ol>

   <img alt="build-code" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/build-code.png"/>
</details>

<details style="margin-left: 40px">	
   <summary><b>Application launch</b></summary>
   <ol>
      <li>After assembling the application, go to the launched module: <code>cd ninja-web</code>;</li>
      <li>Be sure to include the following environment variables in the application launch command:
<pre><code>spring.security.oauth2.client.registration.google.clientId = CLIENT_ID
spring.security.oauth2.client.registration.google.clientSecret = CLIENT_SECRET
app.auth.tokenSecret = TOKEN_SECRET
spring.mail.username = USER_NAME
spring.mail.password = USER_PASSWORD</code>
</pre>
      </li>
      <li>We execute the command: <code>mvn spring-boot:run</code> (<b>see: result below</b>). <b>Default port: 9000</b>.</li>
   </ol>

   <img alt="ninja-started" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/ninja-started.gif"/>
</details>

### ⚙ Includes functionality
___

- Setting up, connecting and working with the **Postgres database**;
- **Using LiquiBase** to create a database structure and initially populate it;
- **Localization** of messages for languages: english and russian;
- Configuring the use of **Thymeleaf templates**;
- Application security using **JWT authorization tokens**;
- **OAuth2** authorization/registration user.

### 🔨 Technology stack
___

- Spring Boot: WEB, Data, Mail, Security, OAuth2 (client & jose);
- Postgres;
- LiquiBase;
- Lombok;
- Docker;
- Other.

### 🎫 License
___

**[Creative Commons Legal Code](https://github.com/Bangerok/Ninja/blob/master/LICENSE)**

_Copyright ©2021, Vladislav [[Bangerok]](https://github.com/Bangerok) Kuznetsov_