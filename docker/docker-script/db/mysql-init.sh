docker build -f MySQL-Dockerfile -t custom-mysql . && \
docker run -d --name custom-mysql-ct -p 3307:3306 custom-mysql && \
docker exec -it custom-mysql-ct bash