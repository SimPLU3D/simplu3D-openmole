library(png)
library(maptools)
library(RColorBrewer)
library(viridis)

path <- "/home/pchapron/dev/simplu3D-openmole/visuPSE/"
setwd(path)


zone <- readShapePoly("parcelle")
plot(zone,col="gray95")

config <- readShapePoly("./PSEshp/run_-1001011140043575811out.shp")

palette <- colorRampPalette(brewer.pal(9, "Blues"))

#fonction pour interpoler la couleur avecf un vecteur de hauteur

interpolColor <- function(vect){
  idxcol <- round(rescale(vect,from=c(0,100)))
  return(palette[idxcol])
}

maxHaut <- 24

qplot(config$Hauteur)
qplot(config$idx)
qplot(config$col)

config$Hauteur
config$idx <-round(rescale(config$Hauteur,from=c(0,maxHaut),to=c(0,100)))
config$col <- sapply(config$idx, FUN = getElement,object=palette)

getElement(palette, config$col)
str(palette)

plot(zone,col="gray95")
plot(config,add=T,col=config$col)



listeValLegend <- paste(as.character(round(seq(from = 0 , to = 24, by=3), digits = 2)), "m", sep=" ")
legend("topright", title="hauteur", legend= listvaleurlegend, fill= palette(length(listeValLegend)), horiz=F, cex=0.8)




split
dev.off()


seq_along(10)


path<- "./PSEshp/run_-1001011140043575811out.shp"




