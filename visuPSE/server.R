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
#library(dplyr)






dfpse <- read.csv("population400000.csv",header = T)  
names(dfpse) <-c("generationEvol",names(dfpse)[2:12],"samplesEvol")
dfpse<- dfpse[,2:12]

dfsimu <- read.csv("energy.csv", header = T, colClasses = c("seed"="character"))

nrow(dfpse)


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
dfpse$moranProfile <- dfsimu$moranProfile[idxSimu]





minis <- apply(dfpse[,1:7],MARGIN = 2,min)
maxis <- apply(dfpse[,1:7],MARGIN = 2,max)
noms <- names(dfpse)[1:7]
bornes <- data.frame(noms, minis, maxis)



# pour ne pas toujours lire le fichier de parcelle
zone <- readShapePoly("parcelle")
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



shinyServer(function(input, output) {
  
  # output$event <- renderPrint({
  #   evd <- event_data("plotly_hover")
  #   if (is.null(evd))
  #     "y rien là "
  #   else {
  #     gini <-  round( dfpse[(evd$pointNumber + 1), 8], digits = 3) 
  #     moran <-  round( dfpse[(evd$pointNumber + 1), 9], digits = 3) 
  #     densite <-  round( dfpse[(evd$pointNumber + 1), 10], digits = 3) 
  #     cov <-  round( dfpse[(evd$pointNumber + 1), 11], digits = 3) 
  #     
  #                   paste(
  #                     "Gini :",gini,
  #                     "Moran :" , moran,
  #                     "Densité :" , densite,
  #                     "Coverage Ratio :",cov
  #                     
  #                   )
  #   }
  #   
  # })
    
  
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
    # currentdfpse <- sample_n(dfpse, size = input$nbpoints)
    # subsetdfpse <- currentdfpse[
    #                   currentdfpse$distReculVoirie >= input$UIreculvoi[1]
    #                    & currentdfpse$distReculVoirie <= input$UIreculvoi[2]
    #                    & currentdfpse$distReculFond >= input$UIreculfon[1]
    #                    & currentdfpse$distReculFond <= input$UIreculfon[2]
    #                    & currentdfpse$distReculLat >= input$UIrecullat[1]
    #                    & currentdfpse$distReculLat <= input$UIrecullat[2]
    #                    & currentdfpse$maximalCES >= input$UIces[1]
    #                    & currentdfpse$maximalCES <= input$UIces[2]
    #                    & currentdfpse$hIniRoad >= input$UIhini[1]
    #                    & currentdfpse$hIniRoad <= input$UIhini[2]
    #                    & currentdfpse$slopeRoad >= input$UIslope[1]
    #                    & currentdfpse$slopeRoad <= input$UIslope[2]
    #                    &currentdfpse$hauteurMax >= input$UIhmax[1]
    #                    & currentdfpse$hauteurMax <= input$UIhmax[2]
    #                   ,]

    
    subsetdfpse <- subsetdfpse()
    
    idxmin <- sapply(subsetdfpse[,colsPointInteret],which.min)
    idxmax <- sapply(subsetdfpse[,colsPointInteret],which.max)
    pointsInteret <- subsetdfpse[append(idxmin,idxmax),colsPointInteret]
    pointsInteret$label <- c("minGini", "minMoran", "minDensite", "minMoranProfile", "maxGini", "maxMoran", "maxDensite", "maxMoranProfile")
    
    
    
    p3d<- plot_ly(subsetdfpse, y = ~gini, x = ~moran, z = ~densite, 
            color= ~moranProfile, 
                mode="markers", 
            type="scatter3d",
            hoverinfo="text",
            text = ~paste("", 'moran: ', round(moran, digits = 3), 
                          '</br> gini: ', round(gini, digits = 3),
                          '</br> densité: ', round(densite, digits  =3) ,
                          '</br> moranProfile:', round(moranProfile, digits = 3)),
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
     lili <- list(src=subsetdfpse[(evd$pointNumber+1),12])
     outfile <- tempfile(fileext='.png')
     
     # Generate a png
     png(outfile, width=400, height=400)
     plot(zone,col="gray95")
     
    config <- readShapePoly(subsetdfpse[(evd$pointNumber+1),12])
    config$idx <-round(rescale(config$Hauteur,from=c(0,maxHaut),to=c(0,100)))
    config$col <- sapply(config$idx, FUN = getElement,object=palette(100))
    
     plot(config, add=T, col=config$col)
     legend("topright", title="hauteur", legend= listeValLegend, fill= palette(length(listeValLegend)), horiz=F, cex=0.8)
     
          dev.off()
     
    list(src = outfile, alt = "")
    
},deleteFile = T )
    
  
  
  output$tabregles<- renderTable({
    evd <- event_data("plotly_hover")
    req(evd)
    
    subsetdfpse<- subsetdfpse()
    currentConfig<- t(subsetdfpse[(evd$pointNumber+1), colsEnCommun])
    currentConfig
  }, colnames = T, rownames = T)
  
  
})

