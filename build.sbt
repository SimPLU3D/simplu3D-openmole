name := "simplu3D-openmole-plugin"

version := "1.0"

scalaVersion := "2.11.6"

osgiSettings

OsgiKeys.exportPackage := Seq("fr.ign.*")

OsgiKeys.importPackage := Seq("*")

OsgiKeys.privatePackage := Seq("")

scalariformSettings

resolvers += "IGN Release" at "https://forge-cogit.ign.fr/nexus/content/repositories/releases/"

resolvers += "IGN Snapshots" at "https://forge-cogit.ign.fr/nexus/content/repositories/snapshots/"

resolvers += "IDB" at "http://igetdb.sourceforge.net/maven2-repository/"

resolvers += "ISC-PIF Public" at "http://maven.iscpif.fr/public/"

resolvers += "ISC-PIF Snapshots" at "http://maven.iscpif.fr/ign-snapshots/"

resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/ign-releases/"


resolvers += "ImageJ" at "http://maven.imagej.net/content/repositories/public"

resolvers += "Central" at "http://repo1.maven.org/maven2/"

resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

resolvers += "osgeo" at "http://download.osgeo.org/webdav/geotools/"

resolvers += "geosolutions" at "http://maven.geo-solutions.it/"

resolvers += "Hibernate" at "http://www.hibernatespatial.org/repository"

val openMOLEVersion = "5.0-SNAPSHOT"

libraryDependencies += "org.openmole" %% "org-openmole-core-dsl" % openMOLEVersion

libraryDependencies += "org.openmole" %% "org-openmole-plugin-task-scala" % openMOLEVersion

val simplu3DVersion = "1.0-SNAPSHOT"

libraryDependencies += "fr.ign.cogit" % "simplu3d" % simplu3DVersion
