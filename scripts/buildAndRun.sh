#!/bin/bash

APP=archive-api
NET=jarand-net

docker build -t $APP .

docker stop $APP || true

docker rm $APP || true

docker run -d --name $APP --network $NET -p 8082:8080 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://$APP-db/$APP-db \
-e SPRING_DATASOURCE_USERNAME=$APP-dbo \
-e SPRING_DATASOURCE_PASSWORD=postgres \
$APP
