enablePlugins(ScalaJSPlugin, WorkbenchPlugin)

name := "BotGL"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

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



//
// SECTION OF SCALA collection to LOOP optimisation
// use this into benchmark - optimisation from https://github.com/nativelibs4java/scalaxy-streams
//-optimise -Yclosure-elim -Yinline
// also if don't like to use collection to loop optimasing use:
//scalaxy.streams.optimize=false

//scalacOptions += "-Xplugin-require:scalaxy-streams"
//
//scalacOptions in Test ~= (_ filterNot (_ == "-Xplugin-require:scalaxy-streams"))
//
//scalacOptions in Test += "-Xplugin-disable:scalaxy-streams"
//
//autoCompilerPlugins := true
//
//addCompilerPlugin("com.nativelibs4java" %% "scalaxy-streams" % "0.3.4")

// Like to live on the wild side? Try the snapshot out:
// addCompilerPlugin("com.nativelibs4java" %% "scalaxy-streams" % "0.4-SNAPSHOT")

// resolvers += Resolver.sonatypeRepo("snapshots")

