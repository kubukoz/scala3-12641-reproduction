val scala3Version = "3.0.0"

lazy val library = project.settings(
  scalaVersion := "2.13.6",
  scalacOptions ++= Seq("-language:higherKinds"),
  libraryDependencies ++= Seq(
    compilerPlugin(
      "org.typelevel" % "kind-projector" % "0.13.0" cross CrossVersion.full
    )
  )
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-simple",
    version := "0.1.0",
    libraryDependencies ++= Seq(
      "org.typelevel" % "cats-effect-kernel" % "3.1.1" cross CrossVersion.for3Use2_13
    ),
    scalaVersion := scala3Version
  )
  .dependsOn(library)
