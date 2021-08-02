Реализация тестового задания "работа с учетными записями" (далее "микросервис") на базе H2/Postgres

Для настроек подключения БД и Kafka используется файл настроек application.properties в модуле /app/src/main/resource

Данный микросервис позволяет принимать json запрос для работы с учетными записями (создание, обновление, блокировка) посредством REST и Kafka.

Предустановленные 

На микросервис реализовано внешнее логгирование посредством Kafka для дополнительного и независимого анализа, сохраняются внутренние ошибки систем, входящие запросы.

Пример входящего json:
{
	"id": NULL - для type = 1, INT - для type = 2,3
	"type": 1/2/3,
	"role" : "роль",
	"fio" : "ФИО",
	"post" : "Должность",
  "userName" : "userName"
}

проект различает 3 типа работы с учетным записями, которые передаются в входном JSON-объекте в свойстве type:
1. микросервис создает новую учетную запись для пользователя, на основании данных в JSON-объекте, генерируется временный пароль
2. микросервис блокирует учетную запись уволенного (isActive = false)
3. микросервис обновляет данные (все кроме пароля) в учетной записи на основании данных в JSON-объекте

В ответ микросервис для type 1/3 возвращает json вида:
{
	"userId": id,
	"userName" : userName,
	"pass" : password
}

для type 2 микросервис возвращает json вида:
{
	"userId": id,
	"userName" : blocked,
	"pass" : blocked
}