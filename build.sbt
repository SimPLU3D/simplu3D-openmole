name := "simplu3D-openmole-plugin"

version := "1.0"

scalaVersion := "2.11.6"

osgiSettings

OsgiKeys.exportPackage := Seq("fr.ign.*")

OsgiKeys.importPackage := Seq("*")

OsgiKeys.privatePackage := Seq("")

scalariformSettings

resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/public/"

resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/ign-snapshots/"

resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/ign-releases/"

val openMOLEVersion = "5.0-SNAPSHOT"

libraryDependencies += "org.openmole" %% "org-openmole-core-dsl" % openMOLEVersion

// libraryDependencies += "org.openmole" %% "org-openmole-plugin-task-scala" % openMOLEVersion

val simplu3DVersion = "1.0-SNAPSHOT"

libraryDependencies += "fr.ign.cogit" % "simplu3d" % simplu3DVersion

libraryDependencies += "fr.ign.cogit" % "simplu3d-rules" % simplu3DVersion

