# Running
Для запуска введите команды по очереди в консоль: `mvn package -DskipTests`, `docker-compose up` 
# Документация к контроллерам
# Регистрация и авторизация
`POST localhost:8080/api/auth/signup`
```
{
    "email": "333@gmail.com",
    "password": "222",
    "role": ["user"]
}
```
`POST localhost:8080/api/auth/signin`
```
{
    "email": "admin@gmail.com",
    "password": "12345678"
}
```
# Работа с записями
`POST localhost:8080/userAddNewTasks/1`
```
{
    "prioritie": "high",
    "status": "pending",
    "heading": "One3213",
    "description": "task"
}
```
`POST localhost:8080/userUpdateTasks/1/1`
```
{
    "prioritie": "low",
    "status": "pending",
    "heading": "3344443",
    "description": "ta333s3333k2"
}
```
`POST localhost:8080/userUpdateExecuter/1/2`
`DELETE localhost:8080/userDeleteTask/1`
`POST localhost:8080/addComments/1`
```
{
    "textComments": "hello"
}
```
`GET localhost:8080/userDeleteTask/1`
`GET localhost:8080/GetUserTask/1?offset=0&limit=10`
`GET localhost:8080/GetAllTaskByEmail/?offset=0&limit=10&email=admin@gmail.com`
`GET localhost:8080/GetAllTaskByEmailExecuter/?offset=0&limit=10&email=333@gmail.com`


