import AssemblyKeys._

assemblySettings

name := "scalaProjectDemo"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0" % "provided"

//libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.1.0"

libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.0.0-mr1-cdh4.2.0" % "provided"

resolvers ++= Seq(
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Spray Repository" at "http://repo.spray.cc/")
