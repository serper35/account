Технологии и их использование:

1. Spring Boot: Используется для создания веб-приложения, управления зависимостями и конфигурацией.
2. Spring Security: Используется для аутентификации и авторизации пользователей.
3. Hibernate: Используется для взаимодействия с базой данных PostgreSQL.
4. PostgreSQL: База данных, развернутая в Docker-контейнере.

Инструкция по запуску:

1. Убедитесь, что у вас установлены Docker и Docker Compose.
2. Клонируйте репозиторий:

   git clone https://github.com/example/banking-service.git

3. Перейдите в директорию проекта:

   cd banking-service

4. Запустите приложение и PostgreSQL-контейнер с помощью Docker Compose:

   docker-compose up -d

5. Приложение будет доступно по адресу http://localhost:8080.

Пользователи и роли:

В базе данных предварительно созданы следующие пользователи:

- Пользователь с ролью ROLE_USER:
  - Логин: user
  - Пароль: password
- Пользователь с ролью ROLE_ADMIN:
  - Логин: admin
  - Пароль: password

Примеры запросов:

1. Получение баланса:

   GET /api/accounts/balance

2. Пополнение баланса:

   POST /api/accounts/deposit?deposit=100.0

3. Снятие денег с баланса:

   POST /api/accounts/withdraw?withdraw=50.0

4. Блокировка учетной записи (доступно только для пользователей с ролью ROLE_ADMIN):

   POST /api/accounts/block

5. Разблокировка учетной записи (доступно только для пользователей с ролью ROLE_ADMIN):

   POST /api/accounts/unblock
