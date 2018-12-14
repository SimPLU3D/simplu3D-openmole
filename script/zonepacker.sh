#!/bin/bash

#Memory arguments
javaargs="-Xms8156m -Xmx8156m -XX:PermSize=512m -XX:MaxPermSize=512m"

#First argument : Parcel shapefile "/home/mbrasebin/Bureau/parcels_rulez/real/parcels_rulez.shp"

#Second argument : temporary folder (must be empty) "/tmp/tmp/"

#Third argument folder out "/tmp/out/"

#Forth argument : the number of simulable parcel by package 20

java $javaargs -cp simplu3d-openmole-plugin_2.12-1.0.jar fr.ign.simplu3d.ZonePackerExecIAUIDF $*

