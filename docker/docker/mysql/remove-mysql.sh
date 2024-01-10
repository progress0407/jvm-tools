docker stop custom-mysql-ct && \
docker rm custom-mysql-ct && \
docker rmi custom-mysql && \
docker image prune -y