library(png)
library(maptools)
library(RColorBrewer)
library(plotly)
library(scales)
library(dplyr)






dfpse <- read.csv("visuPSE/population400000.csv",header = T)  
names(dfpse) <-c("generationEvol",names(dfpse)[2:12],"samplesEvol")
dfpse<- dfpse[,2:12]

dfsimu <- read.csv("visuPSE/energy.csv", header = T, colClasses = c("seed"="character"))



colsEnCommun <- intersect(names(dfsimu), names(dfpse))
#on prend que les colonnes des antécédents 
colsEnCommun <- colsEnCommun[1:7]

lignesPSE <- dfpse[,colsEnCommun]
lignesSimu <- dfsimu[,colsEnCommun]

#hash bidon en concaténant les colonnes
lignesSimu$hash <- apply(lignesSimu, MARGIN = 1 , paste0, collapse="" )
lignesPSE$hash <- apply(lignesPSE, MARGIN = 1 , paste0, collapse="" )


# retourne l'index de la ligne du dataframe simu qui correspond au hash passé en param
matchingPSESimu <- function(hash){
  return(match(hash, lignesSimu$hash))
}


# idx des lignes de dfsimu en matchant les lignes de dfpse
idxSimu <- matchingPSESimu(lignesPSE$hash)

#pour vérifier qu'on a  bien les bons index
#all(dfsimu[idxSimu,colsEnCommun]==dfpse[,colsEnCommun]) 
# doit valoir TRUE

# on cree un attribut "shpPath" dans le dataframePSE (NB : pas de .shp car maptools)


cat("normalisation des paths (ça peut être long)\n")


dfpse$shpPath <- paste("visuPSE/PSEshp/run_",(dfsimu$seed[idxSimu]),"out.shp", sep="")
dfpse$shpPath <- normalizePath(dfpse$shpPath)

#il en manque 4, c'est pas trop génant mais je ne sais pas pourquoi elles n'ont pas matché 
warnings()


dfpse$moranProfile <- dfsimu$moranProfile[idxSimu]






# pour ne pas toujours lire le fichier de parcelle
zone <- readShapePoly("visuPSE/parcelle")
#hauteur max pour couleur des batiments dans le plot SHP
maxHaut <- max(dfpse$hauteurMax)




palette <- colorRampPalette(brewer.pal(9, "Blues"))
listeValLegend <- paste(as.character(round(seq(from = 0 , to = 24, by=3), digits = 2)), "m", sep=" ")


#fonction pour interpoler la couleur avecf un vecteur de hauteur

interpolColor <- function(vect){
  idxcol <- round(rescale(vect,from=c(0,100)))
  return(palette[idxcol])
}


colsPointInteret <- c("gini", "moran", "densite", "moranProfile")



idxmin <- sapply(dfpse[,colsPointInteret],which.min)
idxmax <- sapply(dfpse[,colsPointInteret],which.max)
pointsInteret <- dfpse[append(idxmin,idxmax),colsPointInteret]
pointsInteret$label <- c("minGini", "minMoran", "minDensite", "minMoranProfile", "maxGini", "maxMoran", "maxDensite", "maxMoranProfile")

pointsInteret

minGinishp <- readShapePoly(dfpse$shpPath[3210])
maxGinishp <- readShapePoly(dfpse$shpPath[779])
minMoranshp <- readShapePoly(dfpse$shpPath[3038])
maxMoranshp <- readShapePoly(dfpse$shpPath[2291])
minDensiteshp <- readShapePoly(dfpse$shpPath[2582])
maxDensiteshp <- readShapePoly(dfpse$shpPath[870])


zone["bbox"]


par(mfrow =c(1,2), mar=c(0,0,0,0), xpd=T)

for (config in c(minGinishp, maxGinishp))
{
  config$idx <-
    round(rescale(
      config$Hauteur,
      from = c(0, maxHaut),
      to = c(0, 100)
    ))
  config$col <- sapply(config$idx, FUN = getElement, object = palette(100))
  plot(zone, col = "grey95")
  plot(config, add = T, col = config$col)
}

png("vignetteDensite.png", width = 750, height = 285 )
par(mfrow =c(1,3), mar=c(0,0,0,0) )
layout(matrix(c(1,1,2,2,3), nrow = 1, ncol =5, byrow = TRUE))

for (config in c(minDensiteshp, maxDensiteshp))
{
  config$idx <-
    round(rescale(
      config$Hauteur,
      from = c(0, maxHaut),
      to = c(0, 100)
    ))
  config$col <- sapply(config$idx, FUN = getElement, object = palette(100))
  plot(zone, col = "grey95")
  plot(config, add = T, col = config$col, bty='l')
}
frame()
legend("center",
       inset=c(0,0),
       title = "hauteur",
       legend = listeValLegend,
       fill = palette(length(listeValLegend)),
       horiz = F,
       cex = 1.8
)
dev.off()





layout(matrix(c(1,1,2,2,3), nrow = 1, ncol =5, byrow = TRUE))
plot(1,main=1)
plot(2,main=2)
plot(3,main=3)
plot(4,main=4)
