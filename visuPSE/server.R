#
# This is the server logic of a Shiny web application. You can run the 
# application by clicking 'Run App' above.
#
# Find out more about building applications with Shiny here:
# 
#    http://shiny.rstudio.com/
#

library(shiny)
library(plotly)
library(maptools)
#library(dplyr)





setwd("/home/pchapron/dev/simplu3D-openmole/visuPSE/")
dfpse <- read.csv("population400000.csv",header = T)  
names(dfpse) <-c("generationEvol",names(dfpse)[2:12],"samplesEvol")
dfpse<- dfpse[,2:12]

dfsimu <- read.csv("energy.csv", header = T, colClasses = c("seed"="character"))
str(dfsimu)


colsEnCommun <- intersect(names(dfsimu), names(dfpse))
#on prend que les colonnes des antécédents 
colsEnCommun <- colsEnCommun[1:7]

lignesPSE <- dfpse[,colsEnCommun]
lignesSimu <- dfsimu[,colsEnCommun]

#hash bidon en concaténant les colonnes
lignesSimu$hash <- apply(lignesSimu, MARGIN = 1 , paste0, collapse="" )
lignesPSE$hash <- apply(lignesPSE, MARGIN = 1 , paste0, collapse="" )


# retourne l'index de la ligne du dataframe energy(simu) qui correspond à la list de carac en paramètres
matchingPSESimu <- function(hash){
  return(match(hash, lignesSimu$hash))
}


# idx des lignes de dfsimu en matchant les lignes de dfpse
idxSimu <- matchingPSESimu(lignesPSE$hash)

#pour vérifier qu'on a  bien les bons index
#all(dfsimu[idxSimu,colsEnCommun]==dfpse[,colsEnCommun]) # doit valoir TRUE

# on cree un attribut "shpPath" dans le dataframePSE (NB : pas de .shp car maptools)


cat("normalisation des paths (ça peut être long)\n")


dfpse$shpPath <- paste("PSEshp/run_",(dfsimu$seed[idxSimu]),"out.shp", sep="")




dfpse$shpPath <- normalizePath(dfpse$shpPath)






minis <- apply(dfpse[,1:7],MARGIN = 2,min)
maxis <- apply(dfpse[,1:7],MARGIN = 2,max)
noms <- names(dfpse)[1:7]
bornes <- data.frame(noms, minis, maxis)

names(dfpse)

# pour ne pas toujours lire le fichier de parcelle

zone <- readShapePoly("parcelle")

shinyServer(function(input, output) {
  
   
  
  
  output$event <- renderPrint({
    evd <- event_data("plotly_hover")
    if (is.null(evd))
      "y rien là "
    else {
      gini <-  round( dfpse[(evd$pointNumber + 1), 8], digits = 3) 
      moran <-  round( dfpse[(evd$pointNumber + 1), 9], digits = 3) 
      densite <-  round( dfpse[(evd$pointNumber + 1), 10], digits = 3) 
      cov <-  round( dfpse[(evd$pointNumber + 1), 11], digits = 3) 
      
                    paste(
                      "Gini :",gini,
                      "Moran :" , moran,
                      "Densité :" , densite,
                      "Coverage Ratio :",cov
                    )
    }
    
  })
                                    
  
  output$nuagePlot <- renderPlotly({

    subsetdfpse <- dfpse[
                      dfpse$distReculVoirie >= input$UIreculvoi[1]
                       & dfpse$distReculVoirie <= input$UIreculvoi[2]
                       & dfpse$distReculFond >= input$UIreculfon[1]
                       & dfpse$distReculFond <= input$UIreculfon[2]
                       & dfpse$distReculLat >= input$UIrecullat[1]
                       & dfpse$distReculLat <= input$UIrecullat[2]
                       & dfpse$maximalCES >= input$UIces[1]
                       & dfpse$maximalCES <= input$UIces[2]
                       & dfpse$hIniRoad >= input$UIhini[1]
                       & dfpse$hIniRoad <= input$UIhini[2]
                       & dfpse$slopeRoad >= input$UIslope[1]
                       & dfpse$slopeRoad <= input$UIslope[2]
                       &dfpse$hauteurMax >= input$UIhmax[1]
                       & dfpse$hauteurMax <= input$UIhmax[2]
                      ,]

    plot_ly(subsetdfpse, y = ~gini, x = ~moran, z = ~densite, 
            color= ~coverageRatio, 
                mode="markers", 
            type="scatter3d",
            hoverinfo="text",
            text = ~paste("", 'moran: ', round(moran, digits = 3), 
                          '</br> gini: ', round(gini, digits = 3),
                          '</br> densité: ', round(densite, digits  =3) ,
                          '</br> Coverage', round(coverageRatio, digits = 3)),
             showlegend=T,
            marker = list(size = 2, 
                          opacity=0.5, 
                          line = list(width = 0.1, color="gray25"))
     
    ,width = 500, height = 400)
  })
  
  output$imgConfig <- renderImage({
    evd <- event_data("plotly_hover")
    if (is.null(evd)) 
    {
      lili <- list(src="img/creeper.png")
      }
    else {
   
     lili <- list(src=dfpse[(evd$pointNumber+1),12])
     outfile <- tempfile(fileext='.png')
     
     # Generate a png
     png(outfile, width=400, height=400)
     plot(zone,col="gray95")
    config <- readShapePoly(dfpse[(evd$pointNumber+1),12])
     plot(config, add=T, col="gray25")
     dev.off()
     
      lili <- list(src = outfile,
           alt = "")
     }
    lili
    
},deleteFile = T )
    
  
  
})

plot(rnorm)
dev.off()
