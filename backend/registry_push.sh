full_registry_path="rg.pl-waw.scw.cloud/test-namespace/"
image_name="app"
tag="latest"

docker login rg.pl-waw.scw.cloud/test-namespace -u nologin --password-stdin <<< "d7a8f4ae-a637-4dda-985f-a947abbf7a3f"

docker tag ${image_name}:${tag} ${full_registry_path}${image_name}:${tag}
docker push ${full_registry_path}${image_name}:${tag}