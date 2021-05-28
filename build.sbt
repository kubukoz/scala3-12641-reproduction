val scala3Version = "3.0.0"

lazy val library = project.settings(
  scalaVersion := "2.13.6",
  scalacOptions ++= Seq("-language:higherKinds")
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.1.0",
    scalaVersion := scala3Version
  )
  .dependsOn(library)
