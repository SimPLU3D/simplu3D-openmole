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
library(scales)
library(RColorBrewer)
library(dplyr)
library()



dfpse <- read.csv("population400000.csv",header = T)  
names(dfpse) <-c("generationEvol",names(dfpse)[2:12],"samplesEvol")
dfpse<- dfpse[,2:12]

dfsimu <- read.csv("energy.csv", header = T, colClasses = c("seed"="character"))

# noms des dimensions à afficher dans le nuage de points 3D 
colsPointInteret <- c("gini", "moran", "densite", "coverageRatio")
# label des points min et max des colonnes ci dessus 
labelPointsInteret <- c("minGini", "minMoran", "minDensite", "minCoverage", "maxGini", "maxMoran", "maxDensite", "maxCoverage")


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
all(dfsimu[idxSimu,colsEnCommun]==dfpse[,colsEnCommun]) # doit valoir TRUE

# on cree un attribut "shpPath" dans le dataframePSE (NB : pas de .shp car maptools)


cat("normalisation des paths (ça peut être long)\n")

getwd()
dfpse$shpPath <- paste("PSEshp/run_",(dfsimu$seed[idxSimu]),"out.shp", sep="")

#dfpse$widget3DPath <- paste("../scriptQGIS_genThree.js/output_scenes2/run_",(dfsimu$seed[idxSimu]),"out.html", sep="")
dfpse$widget3DPath <- paste("./data/output_scenes2/run_",(dfsimu$seed[idxSimu]),"out.html", sep="")

#dfpse$widget3DPath <- paste("run_",(dfsimu$seed[idxSimu]),"out.html", sep="")




#sil manque les shp des lignes (3,1020,1344,3200


dfpse$moranProfile <- dfsimu$moranProfile[idxSimu]
dfpse <- dfpse[-c(3,1020,1344,3200),]
dfpse$shpPath <- normalizePath(dfpse$shpPath)
cat("path des shp")
#dfpse$widget3DPath <- normalizePath(dfpse$widget3DPath)
#you nedd to prepend whith file:// for URI
#dfpse$widget3DPath <- paste("file://",(dfpse$widget3DPath), sep="")

print(head(dfpse$widget3DPath))


minis <- apply(dfpse[,1:7],MARGIN = 2,min)
maxis <- apply(dfpse[,1:7],MARGIN = 2,max)
noms <- names(dfpse)[1:7]
bornes <- data.frame(noms, minis, maxis)



dfpse[42, c(12,13)]


# pour ne pas toujours lire le fichier de parcelle
zone <- rgdal::readOGR("parcelle.shp")
#hauteur max pour couleur des batiments dans le plot SHP
maxHaut <- max(dfpse$hauteurMax)




palette <- colorRampPalette(brewer.pal(9, "Blues"))
listeValLegend <- paste(as.character(round(seq(from = 0 , to = 24, by=3), digits = 2)), "m", sep=" ")


#fonction pour interpoler la couleur avecf un vecteur de hauteur

interpolColor <- function(vect){
  idxcol <- round(rescale(vect,from=c(0,100)))
  return(palette[idxcol])
}

fonfont <- list(
  family = "sans serif",
  size = 12,
  color = 'black')



# offlineidxmin <- sapply(dfpse[,colsPointInteret],which.min)
# offlineidxmax <- sapply(dfpse[,colsPointInteret],which.max)
# offlinepointsInteret <- dfpse[append(offlineidxmin,offlineidxmax),append(colsPointInteret, "widget3DPath")]
# offlinepointsInteret$label <- labelPointsInteret
# 
# offlinep3d<- plot_ly(dfpse, y = ~gini, x = ~moran, z = ~densite, 
#               color= ~coverageRatio, 
#               mode="markers", 
#               type="scatter3d",
#               hoverinfo="text",
#               customdata=dfpse$widget3DPath,
#               text = ~paste("", '</br>moran: ', round(moran, digits = 3), 
#                             '</br> gini: ', round(gini, digits = 3),
#                             '</br> densité: ', round(densite, digits  =3) ,
#                             '</br> coverageRatio:', round(coverageRatio, digits = 3)),
#               showlegend=F,
#               marker = list(size = 2, 
#                             opacity=0.5, 
#                             line = list(width = 0.1, color="gray25") )
#               
#               ,width = 500, height = 400)
# offlinep3d <- add_markers(offlinep3d,
#             data=offlinepointsInteret, type="scatter3d",
#             marker=list(size=2, opacity=0.8, color="red", text ),
#             text = ~label, textposition = 'bottom',
#             customdata=offlinepointsInteret$widget3DPath,
#             textfont = list(color = '#FF0000', size = 10), showlegend=F, name="")
# 
# 
# offlinep3d
# 
# htmlwidgets::saveWidget(as_widget(offlinep3d), "widgetNuage.html", selfcontained=F)



shinyServer(function(input, output) {
  options(warn = -1)
  subsetdfpse <- reactive({
    currentdfpse <- sample_n(dfpse, size = input$nbpoints)
    currentdfpse[
      currentdfpse$distReculVoirie >= input$UIreculvoi[1]
      & currentdfpse$distReculVoirie <= input$UIreculvoi[2]
      & currentdfpse$distReculFond >= input$UIreculfon[1]
      & currentdfpse$distReculFond <= input$UIreculfon[2]
      & currentdfpse$distReculLat >= input$UIrecullat[1]
      & currentdfpse$distReculLat <= input$UIrecullat[2]
      & currentdfpse$maximalCES >= input$UIces[1]
      & currentdfpse$maximalCES <= input$UIces[2]
      & currentdfpse$hIniRoad >= input$UIhini[1]
      & currentdfpse$hIniRoad <= input$UIhini[2]
      & currentdfpse$slopeRoad >= input$UIslope[1]
      & currentdfpse$slopeRoad <= input$UIslope[2]
      &currentdfpse$hauteurMax >= input$UIhmax[1]
      & currentdfpse$hauteurMax <= input$UIhmax[2]
      ,]
    
  })
                                  
  
  
  output$nuagePlot <- renderPlotly({
  
    
    subsetdfpse <- subsetdfpse()
    
    idxmin <- sapply(subsetdfpse[,colsPointInteret],which.min)
    idxmax <- sapply(subsetdfpse[,colsPointInteret],which.max)
    pointsInteret <- subsetdfpse[append(idxmin,idxmax),colsPointInteret]
    pointsInteret$label <- labelPointsInteret
    
    
    p3d<- plot_ly(subsetdfpse, y = ~gini, x = ~moran, z = ~densite, 
            color= ~coverageRatio, 
                mode="markers", 
            type="scatter3d",
            hoverinfo="text",
            text = ~paste("", '</br>moran: ', round(moran, digits = 3), 
                          '</br> gini: ', round(gini, digits = 3),
                          '</br> densité: ', round(densite, digits  =3) ,
                          '</br> coverageRatio:', round(coverageRatio, digits = 3)),
             showlegend=F,
            marker = list(size = 2, 
                          opacity=0.5, 
                          line = list(width = 0.1, color="gray25") )
     
    ,width = 500, height = 400)
    add_markers(p3d,
             data=pointsInteret, type="scatter3d",
             marker=list(size=2, opacity=0.8, color="red", text ),
             text = ~label, textposition = 'bottom',
             textfont = list(color = '#FF0000', size = 10), showlegend=F, name="")
 
    })
  
  output$imgConfig <- renderImage({
    evd <- event_data("plotly_hover")
    req(evd)
    subsetdfpse <- subsetdfpse()
     outfile <- tempfile(fileext='.png')
     
     # Generate a png
     png(outfile, width=400, height=400)
     plot(zone,col="gray95")
     
    config <-readShapePoly(subsetdfpse[(evd$pointNumber+1),12])
    config$idx <-round(rescale(config$Hauteur,from=c(0,maxHaut),to=c(0,100)))
    config$col <- sapply(config$idx, FUN = getElement,object=palette(100))
    
     plot(config, add=T, col=config$col)
     legend("topright", title="hauteur", legend= listeValLegend, fill= palette(length(listeValLegend)), horiz=F, cex=0.8)
     
          dev.off()
     
    list(src = outfile, alt = "")
    
},deleteFile = T )
  

  
  
  
  
  
  addResourcePath("outputscenes", "./output_scenes/")
  
 output$widgetQGISThreeJS <- renderUI({
   
   click <- event_data("plotly_click")
   
   subsetdfpse <- subsetdfpse()
  
   visu <- subsetdfpse$widget3DPath[(click$pointNumber+1)]
  
   #my_test <- tags$iframe(src="file://./output_scenes2/run_-1001011140043575811out.html", height=400, width=535)

   req(click)
   if(!identical(visu, character(0)))
    {browseURL(visu)}
   #print(visu)
   #print(my_test)
   # my_test
        })

  
  
  
  
  
  
  
  
  output$tabregles<- renderTable({
    evd <- event_data("plotly_hover")
    req(evd)
    
    subsetdfpse<- subsetdfpse()
    currentConfig<- t(subsetdfpse[(evd$pointNumber+1), colsEnCommun])
    currentConfig
  }, colnames = T, rownames = T)
  
  
})

