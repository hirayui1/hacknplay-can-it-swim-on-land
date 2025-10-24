image_name="app"
tag="latest"

docker build -t "${image_name}:${tag}" .
docker-compose -f compose.yaml up --build -d