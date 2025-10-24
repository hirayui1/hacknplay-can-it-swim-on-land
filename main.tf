terraform {
  required_providers {
    scaleway = {
      source = "scaleway/scaleway"
    }
  }
  required_version = ">= 0.13"
}

provider "scaleway" {
  zone       = "pl-waw-1"
  region     = "pl-waw"
  access_key = "SCWTHXBC9CMY0P2NYWH8"
  secret_key = "d7a8f4ae-a637-4dda-985f-a947abbf7a3f"
  project_id = "0800accf-9d27-4bea-9f6e-5ebe8d4e9e6f"
}

resource "scaleway_container_namespace" "example_namespace" {
  name        = "test-namespace"
  description = "Namespace for my simple NGINX example"
}

resource "scaleway_container" "nginx_web_server" {
  name = "my-simple-nginx"
  registry_image = "nginx:latest"
  port = 80

  min_scale = 1
  namespace_id = scaleway_container_namespace.example_namespace.id
}

output "nginx_url" {
  description = "The public URL for our NGINX web server."
  value       = "https://${scaleway_container.nginx_web_server.domain_name}"
}