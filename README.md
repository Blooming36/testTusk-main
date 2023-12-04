# Running
Для запуска введите команды по очереди в консоль: `mvn package -DskipTests`, `docker-compose up` 
# Документация к контроллерам
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
