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
  project_id = "0800accf-9d27-4bea-9f6e-5ebe8d4e9e6f"
}

resource "scaleway_container_namespace" "example_namespace" {
  name        = "test-namespace"
  description = "Spring app test"
}

variable "AI_API_KEY" {
  type = string
}

resource "scaleway_container" "spring_api_container" {
  name = "spring-boot-api"
  namespace_id = scaleway_container_namespace.example_namespace.id
  registry_image = "rg.pl-waw.scw.cloud/test-namespace/app:latest"
  port = 8080
  cpu_limit    = 250
  memory_limit = 512
  min_scale    = 1
  max_scale    = 1
  environment_variables = {
    "SPRING_PROFILES_ACTIVE" = "prod"
    "AI_API_KEY"             = var.AI_API_KEY
  }
}