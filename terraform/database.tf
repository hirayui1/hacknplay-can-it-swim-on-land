# resource "scaleway_rdb_instance" "spring_db" {
#   name      = "app-db-instance"
#   node_type = "db-dev-s"
#   engine    = "PostgreSQL-14"
#   is_ha_cluster = false
#   user_name = "postgres"
#   password  = "postgres"
# }
#
# resource "scaleway_rdb_database" "app_db" {
#   instance_id = scaleway_rdb_instance.spring_db.id
#   name        = "app_db"
# }