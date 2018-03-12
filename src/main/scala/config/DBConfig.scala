package config

case class DBConfig(
  driver: String,
  user: String,
  password: String,
  database: String,
  jdbcBaseUrl: String
)

object DBConfig {
  import pureconfig._
  def fromConfig: DBConfig = loadConfigOrThrow[DBConfig]("db")
}