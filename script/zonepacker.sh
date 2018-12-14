#!/bin/bash

#Parcel shapefile
parcelfile="/home/mbrasebin/Bureau/parcels_rulez/real/parcels_rulez.shp"

#Temporary folder (has to be empty)
tempfolder="/tmp/tmp/"

#OutputFolder
outfolder="/tmp/out/"

#Nuber of simulable parcels per final package
numberofsimulbypackage=20

#Mememory arguments
javaargs="-Xms8156m -Xmx8156m -XX:PermSize=512m -XX:MaxPermSize=512m"

java $javaargs -cp simplu3d-openmole-plugin_2.12-1.0.jar fr.ign.simplu3d.ZonePackerExecIAUIDF $parcelfile  $tempfolder $outfolder $numberofsimulbypackage

