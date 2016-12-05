library(png)
library(maptools)

path <- "/home/pchapron/dev/simplu3D-openmole/visuPSE/"
setwd(path)


zone <- readShapePoly("parcelle")
plot(zone,col="gray95")

config <- readShapePoly("config")
plot(config, add=T, col="gray25")


path<- "./PSEshp/run_-1001011140043575811out.shp"
p2 <- normalizePath(path)



