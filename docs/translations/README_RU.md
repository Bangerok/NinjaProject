<!--suppress HtmlDeprecatedAttribute -->
<div align="center">
    <h1>
        <a href="https://ninjaenterprise.github.io/Ninja/">Ninja</a>
    </h1>
</div>

<div align="center">
    <a href="https://github.com/NinjaEnterprise/Ninja/blob/master/docs/README.md">
        <img alt="english-version" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/languages/english.png"/>
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

### 📖 Описание
___

Серверная часть для подключения к клиентскому интерфейсу. Пока только для обучения. Состоит из 4 модулей:
1. **[NinjaDatabase](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-database)**. Настройка и работа с базой 
   данных. Включает такие аспекты:
    - **PostgreSQL**: конфигурация источника данных;
    - Конфигурация **LiquiBase**;
    - Запуск скрипта для создания структуры базы данных и заполнение ее данными;
    - Модели данных и репозитории для работы с ними.


2. **[NinjaCore](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-core)**. Базовые сервисы для работы с 
   приложением. Включает такие аспекты:
    - Конфигурация локализации приложения для двух языков: **английский** и **русский**;
    - Конфигурация **отправки сообщений на e-mail**. Для шаблонов сообщений используется — **thymeleaf**;
    - Общие сервисы для взаимодействия с базой данных.


3. **[NinjaSecurity](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-security)**. Безопасность приложения
   (планируется выделение этого модуля как отдельного микро сервиса). Включает такие аспекты: 
    - Конфигурация **OAuth2** авторизации;
    - Конфигурация генерации и выдачи **JWT** токенов при авторизации.


4. **[NinjaWeb](https://github.com/NinjaEnterprise/Ninja/tree/master/ninja-web)**. Обеспечивает REST взаимодействие с 
   приложением через контроллеры. Включает такие аспекты:
    - Взаимодействие с приложением через контроллеры. Например, для авторизации;
    - Проверка данных, полученных от клиента;
    - Запуск Spring Boot приложения.

### ❗ Требования
___

* **JDK**: 16 и выше;
* **Docker**.

### 📋 Инструкция по запуску
___

*Все команды выполняются в консоли. Для всех действий можно использовать IDE, но кто это знает — тот так и будет делать
и инструкция ему не нужна.*
<details style="margin-left: 40px">	
   <summary><b>Клонирование репозитория</b></summary>

   1. Создаем папку: `mkdir GitProjects` (имя папки может быть любым, но вам нужно будет продолжать использовать только 
      его);
   2. Переходим в папку: `cd GitProjects`;
   3. Клонируем репозиторий: `git clone https://github.com/NinjaEnterprise/Ninja.git`;
   4. Переходим в созданную папку: `cd Ninja`;
   5. Выполнено (**см.: результат ниже**).

   <img alt="clone-repo" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/clone-repository.png"/>
</details>

<details style="margin-left: 40px">	
   <summary><b>Настройка базы данных</b></summary>

   *После клонирования репозитория — мы уже должны быть в папке проекта.*
   1. Для работы Docker нужно его сначала **[установить](https://docs.docker.com/engine/install/)**;
   2. Чтобы настроить базу данных, необходимую для работы приложения, переходим в папку: `cd docker\postgresql`;
   3. Запускаем .bat файл командой: `postgres.bat` (**см.: результат ниже**). *Стандартный порт: 5024*

   <img alt="ninja-docker" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/ninja-docker.gif"/>
   <img alt="docker-info" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/docker-info.png"/>
</details>


<details style="margin-left: 40px">	
   <summary><b>Сборка проекта</b></summary>

   1. После запуска Docker и настройки базы данных возвращаемся в корень проекта: `cd ..\..`;
   2. Выполняем команду: `mvn clean install` (**см.: результат ниже**).

   <img alt="build-code" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/build-code.png"/>
</details>

<details style="margin-left: 40px">	
   <summary><b>Запуск приложения</b></summary>

   1. После сборки приложения переходим в модуль для его запуска: `cd ninja-web`;
   2. Нужно обязательно включить следующие переменные среды в команду запуска приложения:
   ```
   spring.security.oauth2.client.registration.google.clientId = <CLIENT_ID>
   spring.security.oauth2.client.registration.google.clientSecret = <CLIENT_SECRET>
   app.auth.tokenSecret = <TOKEN_SECRET>
   spring.mail.username = <USER_NAME>
   spring.mail.password = <USER_PASSWORD>
   ```
   3. Выполняем команду: `mvn spring-boot:run` (**см.: результат ниже**). *Стандартный порт: 9000*.
   ```java
   @SpringBootApplication
   @ConfigurationPropertiesScan("ru.bangerok.enterprise.ninja.config.properties")
   public class NinjaWebApplication {
   
      public static void main(String[] args) { // Используется для запуска приложения
        run(NinjaWebApplication.class, args);
      }
   } 
```

   <img alt="ninja-started" src="https://raw.githubusercontent.com/NinjaEnterprise/Ninja/master/assets/startup/ninja-started.gif"/>
</details>

### ⚙ Включает функциональность
___

- Настройка, подключение и работа с базой данных **Postgres**;
- **Использование LiquiBase** для создания структуры базы данных и ее первоначального заполнения;
- **Локализация** сообщений для языков: английский и русский;
- Настройка **шаблонов Thymeleaf**;
- Безопасность приложения с использованием **JWT**;
- **OAuth2** авторизация/регистрация пользователя.

### 🔨 Стек технологий
___

- Spring Boot: WEB, Data, Mail, Security, OAuth2 (client & jose);
- Postgres;
- LiquiBase;
- Lombok;
- Docker;
- И другие.

### 🎫 Лицензия
___

**[Creative Commons Legal Code](https://github.com/Bangerok/Ninja/blob/master/LICENSE)**

_Copyright ©2021, Vladislav [[Bangerok]](https://github.com/Bangerok) Kuznetsov_