name := "typelevel-service"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(FlywayPlugin)

val Http4sVersion = "0.18.0"
libraryDependencies ++= Seq(
  "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion
//  "ch.qos.logback"  %  "logback-classic"     % LogbackVersion
)

val DoobieVersion = "0.5.1"
libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core" % DoobieVersion,
  "org.tpolecat" %% "doobie-postgres" % DoobieVersion
)

/*
* DB Migration
* */
flywayUrl := "jdbc:postgresql:postgres"
flywayLocations += "db/migration"
flywayUser := "monkeyman"
flywayPassword := "monkeypassword"
