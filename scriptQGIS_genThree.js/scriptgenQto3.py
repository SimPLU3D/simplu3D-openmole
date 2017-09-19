from PyQt4.QtCore import QSize,SIGNAL, Qt
from qgis.core import QgsCoordinateReferenceSystem, QgsCoordinateTransform, QgsPoint
from Qgis2threejs.api import Exporter
from qgis.gui import *
from PyQt4.QtGui import QAction, QMainWindow



import os
import io

firstlayer = iface.addVectorLayer("/home/paul/dev/simplu3D-openmole/visuPSE/PSEshp/run_-3070707356043524019out.shp", "tutu", "ogr")
if not firstlayer:
  print "Layer failed to load!"
  

#integrer couche bati existant , couleur #dbdbdb

crs = QgsCoordinateReferenceSystem(2154, QgsCoordinateReferenceSystem.EpsgCrsId)
print crs.isValid()


# fond de carte OSM

#try:
#    olplugin = qgis.utils.plugins['openlayers_plugin']
#    print "openlayers plugin charged"
#    ol_gphyslayertype = olplugin._olLayerTypeRegistry.getById(4)
#    
#    olplugin.setMapCrs(crs)
#    print "layer OSM ajout"
#    
#    olplugin.addLayer(ol_gphyslayertype)
#    
#except KeyError:
#    print 'OpenLayers plugin not loaded.'



#call to plugins Qgis2 Three JS
#il faut le faire une fois a la main pour qu'il garde le reglage du polygon extrude.


# Output filename template
path_tmpl = "/home/chap/dev/simplu3D-openmole/scriptQGIS_genThree.js/output_scenes2/{0}.html"

# Coordinate transformer: WGS 84 to JGD2000 / UTM zone 53N
wgs84 = QgsCoordinateReferenceSystem(4326, QgsCoordinateReferenceSystem.EpsgCrsId)
vabien = QgsCoordinateReferenceSystem(2154, QgsCoordinateReferenceSystem.EpsgCrsId)
transform = QgsCoordinateTransform(wgs84, vabien)
print "transformation loaded"
canvas = iface.mapCanvas()
canvas.enableAntiAliasing(True)
canvas.zoomByFactor(0.8)
canvas.setCrsTransformEnabled(True)
canvas.setDestinationCrs(vabien)


shpfilesPath = "/home/chap/dev/simplu3D-openmole/visuPSE/PSEshp/"

mylayers = list()
filenames =list()




expleSettings = open("/home/chap/dev/simplu3D-openmole/scriptQGIS_genThree.js/settingsExample.qto3settings",'r')
settingsdata = expleSettings.read()
expleSettings.close()


i=0


for file in os.listdir(shpfilesPath):
    if file.endswith(".shp"):
        i=i+1
        filename= os.path.splitext(os.path.basename(file))[0]
        
        abspathfile = os.path.join(shpfilesPath,file)
       
#        try:
#            olplugin = qgis.utils.plugins['openlayers_plugin']
#            print "openlayers plugin charged"
#            ol_gphyslayertype = olplugin._olLayerTypeRegistry.getById(4)
#            
#            olplugin.setMapCrs(crs)
#            print "layer OSM ajout"
#            
#            #olplugin.addLayer(ol_gphyslayertype)
#            
#        except KeyError:
#            print 'OpenLayers plugin not loaded.'
        
        
        #QgsMapLayerRegistry.instance().removeAllMapLayers()
         
        
        layer = iface.addVectorLayer(abspathfile, filename, "ogr")
        layer.setCrs(crs)
        layerID = layer.id()
    
        #olplugin.addLayer(ol_gphyslayertype)
        #print layer
        if not layer:
            print "Layer failed to load!"
        # Make sure that map canvas CRS is EPSG:3099
        layer.loadNamedStyle("/home/chap/dev/simplu3D-openmole/scriptQGIS_genThree.js/classifHauteur.qml")
        
        # Get map settings from the map canvas
        mapSettings = canvas.mapSettings()
        canvas.refresh()
        # Canvas size (base image size)
        canvasSize = QSize(1000, 1000)
        mapSettings.setOutputSize(canvasSize)

        # Size of extent, and rotation
        #width = 10000.
        #height = width * canvasSize.height() / canvasSize.width()

        # charge les settings pour exporter la couche courrante
        newdata = settingsdata.replace("FFFFFF",filename)
        newdata = newdata.replace("XXXXXX", layerID)

        expleSettings = open("/home/chap/dev/simplu3D-openmole/scriptQGIS_genThree.js/settingsExampleModified.qto3settings",'w')
        expleSettings.write(newdata)
        expleSettings.close()
        newSettingPath = "/home/chap/dev/simplu3D-openmole/scriptQGIS_genThree.js/settingsExampleModified.qto3settings"

        # Create an exporter
        exporter = Exporter(iface, newSettingPath)
        exporter.setMapSettings(mapSettings)
        #print "exporter instance"
        # Coordinate transform
        center = canvas.center()
        
        # Set extent and rotation
        rotation = 0
        #exporter.setExtent(center, canvas.width(), canvas.height(), rotation)
        exporter.setExtent(center, 700, 500, rotation)
        
        # Output HTML file path
        filepath = path_tmpl.format(filename)
        
        # Export
        err = exporter.export(filepath, openBrowser=False)
        #if err == Exporter.NO_ERROR:
          #print "{0} has been exported to {1}".format(filename, filepath)
        #else:
         # print "Failed to export {0}: {1}".format(filename, err)

        QgsMapLayerRegistry.instance().removeMapLayer(layer.id())
        #canvas.refresh() 
        if i%100 == 0 :
            print i
    

print "tada!"




