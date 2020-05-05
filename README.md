Спочатку потрібно клонувати https://github.com/GeoFoxit/JavaMicroServices_smtrpzConfig, щоб локально встановити конфіг репозиторій.
Для запуску додатка потрібно послідовно відкрити всі 4 проекти в Intellij Idea та запустити їх.

Та запустити в докері інстанс бази данних:
docker run --name mypostgres -e POSTGRES_PASSWORD=potgres -e POSTGRES_DATABASE=smtrpz -p 5432:5432 -it postgres:9.4

Eureka Server URL: http://localhost:8761
Service URL (instance 1): http://localhost:8081
Service URL (instance 2): http://localhost:8082
Config Server: http://localhost:8888
ApiGateWay: http://localhost:9999
