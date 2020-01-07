# image-store-service

** Run the application by ** 

```` 
java -jar image-store-service-0.0.1-SNAPSHOT.jar
````


> Service exposed URLS

    - GET http://localhost:9098/album
    - DELETE http://localhost:9098/album/{albumName}
    - POST http://localhost:9098/album/{albumName}
    - GET http://localhost:9098/album/{albumName}/image
    - POST http://localhost:9098/album/{albumName}/image
    - DELETE http://localhost:9098/album/{albumName}/image/{imageId}
    - GET http://localhost:9098/album/{albumName}/image/{imageId}
    
> Swagger URL

    - http://localhost:9098/v2/api-docs
    - http://localhost:9098/swagger-ui.html

> H2 Data Base URL

    -   http://localhost:9098/h2-console

**  How To Start rabbitmq **

* To Start Rabbitmq run ``docker-compose up`` this command on cmd where docker is install and rabbit mq will start beacuse ``docker-compose.yml`` file is added

** How To Make Docker Container **

-   You need to Run the following command to make docker image
    - $ docker build -t <docker-hub-id>/<imagename> <directory>
    - $ docker build -t msdeo/image-store-service .
    
** Start The Container **
-   ``docker run -d -p 9098:9098 -name image --hostname local-image msdeo/image-store-service``