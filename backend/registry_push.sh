full_registry_path="rg.pl-waw.scw.cloud/test-namespace/"
image_name="app"
tag="latest"
api_key="099695df-4b5f-45c3-b563-a8765ba6d36b"

docker login rg.pl-waw.scw.cloud/test-namespace -u nologin --password-stdin <<< ${api_key}
docker tag ${image_name}:${tag} ${full_registry_path}${image_name}:${tag}
docker push ${full_registry_path}${image_name}:${tag}