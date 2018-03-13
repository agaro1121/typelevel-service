name := "typelevel-service"

version := "0.1"

scalaVersion := "2.12.4"

enablePlugins(FlywayPlugin)

val Http4sVersion = "0.18.0"
libraryDependencies ++= Seq(
  "org.http4s"      %% "http4s-blaze-server" % Http4sVersion,
  "org.http4s"      %% "http4s-circe"        % Http4sVersion,
  "org.http4s"      %% "http4s-dsl"          % Http4sVersion
)
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

val DoobieVersion = "0.5.1"
libraryDependencies ++= Seq(
  "org.tpolecat" %% "doobie-core" % DoobieVersion,
  "org.tpolecat" %% "doobie-postgres" % DoobieVersion
)

libraryDependencies += "joda-time" % "joda-time" % "2.9.9"

libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.9.0"

//val MonixVersion = "2.3.3"
val MonixVersion = "3.0.0-M3"
libraryDependencies ++= Seq(
  "io.monix" %% "monix" % MonixVersion
//  "io.monix" %% "monix-eval" % MonixVersion,
//  "io.monix" %% "monix-types" % MonixVersion,
//  "io.monix" %% "monix-cats" % MonixVersion
)

val CirceVersion = "0.9.1"
libraryDependencies ++= Seq(
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-jawn" % CirceVersion
)


/*
* DB Migration
* */
flywayUrl := "jdbc:postgresql:postgres"
flywayLocations += "db/migration"
flywayUser := "monkeyman"
flywayPassword := "monkeypassword"
