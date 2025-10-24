# // Creates a managed database instance (the server).
# resource "scaleway_rdb_instance" "spring_db_instance" {
#   name        = "spring-project-db"
#   node_type   = "db-dev-s"
#   engine      = "PostgreSQL-14"
#   is_ha_cluster = false
#   user_name   = "postgres"
#   password    = "postgres"
# }
#
# // Creates the actual database (schema) inside the instance we just defined.
# resource "scaleway_rdb_database" "spring_db" {
#   instance_id = scaleway_rdb_instance.spring_db_instance.id
#   name        = "spring_app_db"
# }