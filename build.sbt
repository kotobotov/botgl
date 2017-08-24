enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "BotGL"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

resolvers += Resolver.jcenterRepo
libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "latest.integration",
  "com.lihaoyi" %%% "scalatags" % "latest.integration",
  "be.doeraene" %%% "scalajs-jquery" % "latest.integration",
  "com.github.karasiq" %%% "scalajs-bootstrap" % "2.0.0",
"com.definitelyscala" %%% "scala-js-less" % "1.0.2"
)
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"

libraryDependencies += "com.lihaoyi" %% "acyclic" % "0.1.5" % "provided"

libraryDependencies += "com.lihaoyi" %%% "upickle" % "latest.integration"

libraryDependencies += "com.lihaoyi" %%% "scalarx" % "latest.integration"

libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.9.2"