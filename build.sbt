import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scala-mapr-hive",
    libraryDependencies ++= Seq(
      scalaTest % Test
    ),
    assemblyJarName in assembly := "ali-hive-connector.jar"
  )
