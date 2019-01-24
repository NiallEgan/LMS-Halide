scalaVersion := "2.11.2"
scalaOrganization := "org.scala-lang.virtualized"

resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies += "org.scala-lang.lms" %% "lms-core" % "1.0.0-SNAPSHOT"

libraryDependencies += "org.scala-lang.virtualized" % "scala-compiler" % "2.11.2"

libraryDependencies += "org.scala-lang.virtualized" % "scala-library" % "2.11.2"

libraryDependencies += "org.scala-lang.virtualized" % "scala-reflect" % "2.11.2"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.2"

libraryDependencies += "ch.ethz.acl" %% "scala-unsigned" % "0.1-SNAPSHOT"
libraryDependencies += "ch.ethz.acl" %% "lms-intrinsics" % "0.0.5-SNAPSHOT"


scalacOptions += "-Yvirtualize"
scalacOptions += "-deprecation"
scalacOptions += "-feature"
scalacOptions += "-language:implicitConversions"
