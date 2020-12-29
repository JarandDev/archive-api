#!/bin/bash

APP=archive-api
NET=jarand-net

docker network create jarand-net || true

docker stop $APP-db || true

docker rm $APP-db || true

docker run -d --name $APP-db --network $NET -p 5434:5432 \
-e POSTGRES_DB=$APP-db \
-e POSTGRES_USER=$APP-dbo \
-e POSTGRES_PASSWORD=postgres \
postgres
