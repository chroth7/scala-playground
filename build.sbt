ThisBuild / version      := "0.1.0"
ThisBuild / scalaVersion := "2.12.7"
ThisBuild / organization := "ch.ambrite"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
val gigahorse = "com.eed3si9n" %% "gigahorse-okhttp" % "0.3.1"
val playJson  = "com.typesafe.play" %% "play-json" % "2.6.9"

lazy val hello = (project in file("."))
  .aggregate(helloCore)
  .aggregate(underscore4)
  .aggregate(underscore5)
  .dependsOn(helloCore)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Hello",
    libraryDependencies += scalaTest % Test,
  )

lazy val helloCore = (project in file("core"))
  .settings(
    name := "Hello Core",
    libraryDependencies ++= Seq(gigahorse, playJson),
    libraryDependencies += scalaTest % Test,
  )

lazy val underscore4 = (project in file("underscore4"))
  .settings(
    name := "Underscore Chapter 4",
    libraryDependencies += scalaTest % Test,
  )

lazy val underscore5 = (project in file("underscore5"))
  .settings(
    name := "Underscore Chapter 5",
    libraryDependencies += scalaTest % Test,
  )

