Проект Ninja
=============================

### Описание

Состоит из двух модулей - клиентская часть и серверная. Называются соответственно.

- Первый модуль написан на VueJS, зависимости которого можно посмотреть в файле - vue.config.js.
- Второй модуль написан с использованием spring-boot-2.5.0. Остальные зависимости можно посмотреть в
  файле - pom.xml. Используется авторизация через JWT токены, которую поддерживает так же и oauth2
  авторизация через Google.

### Инструкция по запуску

- Сделать **clone** проекта;
- Выполнить команду **npm run serve** в корне клиентского модуля;
- Выполнить команду **mvn spring-boot:run** в корне серверного модуля;
- Будут запущены два сервера на разных портах- **клиент на 3000**, а **сервер на 9000** *(поменять
  порты можно, если хочется)*.
