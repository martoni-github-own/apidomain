#/bin/sh
docker volume create apidomain-data
docker run --name apidomain-postgre --env-file .env -d -p 54320:5432 -v apidomain-data:/var/lib/postgresql/data postgres:12.9-alpine