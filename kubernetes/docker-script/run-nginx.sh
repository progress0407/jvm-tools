docker run \
  --name nginx \
  -p 80:80 \
  -v ./config:/etc/nginx/conf.d \
  -d \
  nginx
