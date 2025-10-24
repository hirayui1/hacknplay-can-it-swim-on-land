# // A namespace is just a grouping for our containers.
# resource "scaleway_container_namespace" "app_namespace" {
#   name = "spring-project-namespace"
# }
#
# // Deploy the Spring Web API container.
# resource "scaleway_container" "spring_api" {
#   name          = "spring-web-api"
#   namespace_id  = scaleway_container_namespace.app_namespace.id
#
#   // IMPORTANT: Replace this with the path to YOUR image in YOUR registry.
#   registry_image = "your-dockerhub-username/your-spring-api-image:latest"
#   port           = 8080 // The port your Spring app runs on INSIDE the container.
#
#   // Environment variables passed to your Spring application.
#   // This is how we connect the API to the database!
#   environment_variables = {
#     // Terraform automatically gets the IP, port, etc., from the database resource.
#     "SPRING_DATASOURCE_URL"      = "jdbc:postgresql://${scaleway_rdb_instance.spring_db_instance.endpoint_ip}:${scaleway_rdb_instance.spring_db_instance.endpoint_port}/${scaleway_rdb_database.spring_db.name}"
#     "SPRING_DATASOURCE_USERNAME" = scaleway_rdb_instance.spring_db_instance.user_name
#     "SPRING_DATASOURCE_PASSWORD" = scaleway_rdb_instance.spring_db_instance.password
#   }
}

# // Deploy the Frontend container.
# resource "scaleway_container" "frontend" {
#   name          = "frontend-app"
#   namespace_id  = scaleway_container_namespace.app_namespace.id
#
#   // IMPORTANT: Replace this with the path to YOUR frontend image.
#   registry_image = "your-dockerhub-username/your-frontend-image:latest"
#   port           = 80 // The port your frontend serves on (e.g., Nginx default).
#
#   // Pass the URL of our API to the frontend app.
#   environment_variables = {
#     // The name of this variable depends on your frontend code (e.g., REACT_APP_API_URL).
#     "API_URL" = "https://${scaleway_container.spring_api.domain_name}"
#   }
# }