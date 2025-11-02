image_name="app"
tag="latest"

#gradle build
docker build -t "${image_name}:${tag}" .
# docker-compose -f compose.yaml up
# docker-compose -f compose.yaml down