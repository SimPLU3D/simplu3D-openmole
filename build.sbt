name := "simplu3D-openmole-plugin"

version := "1.0"

scalaVersion := "2.12.6"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
scalacOptions += "-target:jvm-1.8"

enablePlugins(SbtOsgi)

OsgiKeys.exportPackage := Seq("simplu3dopenmoleplugin.*")

OsgiKeys.importPackage := Seq("*;resolution:=optional")

OsgiKeys.privatePackage := Seq("""
|!scala.*,!java.*,META-INF.*;-split-package:=merge-first,
|*;-split-package:=merge-first
|""".stripMargin)

OsgiKeys.requireCapability := """osgi.ee; osgi.ee="JavaSE";version:List="1.8,1.9"""""


resolvers += "IDB" at "http://igetdb.sourceforge.net/maven2-repository/"

//resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

resolvers += Resolver.mavenLocal

resolvers += "IGN snapshots" at "https://forge-cogit.ign.fr/nexus/content/repositories/snapshots/"

resolvers += "IGN releases" at "https://forge-cogit.ign.fr/nexus/content/repositories/releases/"

//resolvers += "ISC-PIF Public" at "http://maven.iscpif.fr/public/"

//resolvers += "ISC-PIF Snapshots" at "http://maven.iscpif.fr/ign-snapshots/"

//resolvers += "ISC-PIF Release" at "http://maven.iscpif.fr/ign-releases/"

resolvers += "ImageJ" at "http://maven.imagej.net/content/repositories/public"


resolvers += "Boundless" at "http://repo.boundlessgeo.com/main"

resolvers += "osgeo" at "http://download.osgeo.org/webdav/geotools/"

resolvers += "geosolutions" at "http://maven.geo-solutions.it/"

resolvers += "Hibernate" at "http://www.hibernatespatial.org/repository"

//val openMOLEVersion = "5.0-SNAPSHOT"

//libraryDependencies += "org.openmole" %% "org-openmole-core-dsl" % openMOLEVersion

//libraryDependencies += "org.openmole" %% "org-openmole-plugin-task-scala" % openMOLEVersion

val simplu3DVersion = "1.2-SNAPSHOT"

val geotoolsGridVersion = "18.4"

libraryDependencies += "org.geotools" % "gt-grid" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-coverage" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-geotiff" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-image" % geotoolsGridVersion
//
libraryDependencies += "org.geotools" % "gt-referencing" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-epsg-hsql" % geotoolsGridVersion
//libraryDependencies += "org.geotools" % "gt-epsg-extension" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-shapefile" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-graph" % geotoolsGridVersion
//
libraryDependencies += "org.geotools" % "gt-metadata" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-opengis" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-main" % geotoolsGridVersion
libraryDependencies += "org.geotools" % "gt-api" % geotoolsGridVersion



libraryDependencies += "fr.ign.cogit" % "simplu3d-experiments" % simplu3DVersion excludeAll(
    ExclusionRule(organization = "org.geotools"),
    ExclusionRule(organization = "uk.ac.ed.ph.snuggletex"),
    ExclusionRule(organization = "vigna.dsi.unimi.it"),
    ExclusionRule(organization = "net.billylieurance.azuresearch"),
    ExclusionRule(organization = "net.sf.jafama"),
    ExclusionRule(organization = "net.sourceforge.jmatio"),
    ExclusionRule(organization = "jgrapht"),
    ExclusionRule(organization = "org.bethecoder"),
    ExclusionRule(organization = "com.aetrion.flickr"),
    ExclusionRule(organization = "com.caffeineowl.graphics"),
    ExclusionRule(organization = "de.bwaldvogel"),
    ExclusionRule(organization = "mnstarfire"),
    ExclusionRule(organization = "com.thoughtworks.xstream"),
    ExclusionRule(organization = "jfree"),
    ExclusionRule(organization = "javax.media"),
    ExclusionRule(organization = "org.slf4j")
  )
