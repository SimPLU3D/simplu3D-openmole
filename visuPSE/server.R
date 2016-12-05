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
library(dplyr)


options(scipen = 999)

setwd("/home/pchapron/dev/simplu3D-openmole/visuPSE/")
df <- read.csv("population400000.csv",header = T)  
names(df) <-c("generationEvol",names(df)[2:12],"samplesEvol")
df<- df[,2:12]





# #il faut les path en dur ?
# pathimg <- normalizePath("img/spaceinvaders.png")
# pathimg2 <- normalizePath("img/spaceinvaders2.png")
# pathimg3 <- normalizePath("img/spaceinvaders3.png")
# pathimg4 <- normalizePath("img/spaceinvaders4.png")
# 
# 
# listpath <- rep_len(c(pathimg,pathimg2,pathimg3,pathimg4), length.out = nrow(df))
# df$pathimg <- listpath




minis <- apply(df[,1:7],MARGIN = 2,min)
maxis <- apply(df[,1:7],MARGIN = 2,max)
noms <- names(df)[1:7]
bornes <- data.frame(noms, minis, maxis)

names(df)



shinyServer(function(input, output) {
  
   
  
  output$event <- renderPrint({
    evd <- event_data("plotly_click")
    if (is.null(evd)) "y rien là " else paste("config à la ligne ",evd$pointNumber+1) 
  })
  
  
  output$nuagePlot <- renderPlotly({

    subsetdf <- df[
                      df$distReculVoirie >= input$UIreculvoi[1]
                       & df$distReculVoirie <= input$UIreculvoi[2]
                       & df$distReculFond >= input$UIreculfon[1]
                       & df$distReculFond <= input$UIreculfon[2]
                       & df$distReculLat >= input$UIrecullat[1]
                       & df$distReculLat <= input$UIrecullat[2]
                       & df$maximalCES >= input$UIces[1]
                       & df$maximalCES <= input$UIces[2]
                       & df$hIniRoad >= input$UIhini[1]
                       & df$hIniRoad <= input$UIhini[2]
                       & df$slopeRoad >= input$UIslope[1]
                       & df$slopeRoad <= input$UIslope[2]
                       &df$hauteurMax >= input$UIhmax[1]
                       & df$hauteurMax <= input$UIhmax[2]
                      ,]

    plot_ly(subsetdf, y = ~gini, x = ~moran, z = ~densite, 
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
      lili <- list(src="/home/pchapron/dev/applishiny/simpluPSE/img/creeper.png")
      }
    else {
   
     lili <- list(src=df[(evd$pointNumber+1),12])
    }
    
    lili
    
},deleteFile = F )
    
  
  
})



