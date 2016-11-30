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





#il faut les path en dur ?
pathimg <- normalizePath("img/spaceinvaders.png")
pathimg2 <- normalizePath("img/spaceinvaders2.png")
pathimg3 <- normalizePath("img/spaceinvaders3.png")
pathimg4 <- normalizePath("img/spaceinvaders4.png")


listpath <- rep_len(c(pathimg,pathimg2,pathimg3,pathimg4), length.out = nrow(df))
df$pathimg <- listpath

minis <- apply(df[,1:7],MARGIN = 2,min)
maxis <- apply(df[,1:7],MARGIN = 2,max)
noms <- names(df)[1:7]
bornes <- data.frame(noms, minis, maxis)

names(df)
# Define server logic required to draw a histogram
shinyServer(function(input, output) {
  
   
  
  output$event <- renderPrint({
    evd <- event_data("plotly_click")
    if (is.null(evd)) "y rien là " else paste("config à la ligne ",evd$pointNumber+1) 
  })
  
  
  # output$slReculVoi <-  renderUI({
  #   sliderInput(inputId = "UIreculvoi", 
  #               label = bornes$noms[1],
  #               min = bornes$minis[1], 
  #               max= bornes$maxis[1], 
  #               value=c(bornes$minis[1],bornes$maxis[1]) )
  #  
  # })
  # 
  # output$slReculFon <-  renderUI({
  #   sliderInput(inputId = "UIreculfon", 
  #               label = bornes$noms[2],
  #               min = bornes$minis[2], 
  #               max= bornes$maxis[2], 
  #               value=c(bornes$minis[2],bornes$maxis[2]) )
  #   
  # })
  # 
  # output$slReculLat <-  renderUI({
  #   sliderInput(inputId = "UIrecullat", 
  #               label = bornes$noms[3],
  #               min = bornes$minis[3], 
  #               max= bornes$maxis[3], 
  #               value=c(bornes$minis[3],bornes$maxis[3]) )
  #   
  # })
  # 
  # output$slCES <-  renderUI({
  #   sliderInput(inputId = "UIces", 
  #               label = bornes$noms[4],
  #               min = bornes$minis[4], 
  #               max= bornes$maxis[4], 
  #               value=c(bornes$minis[4],bornes$maxis[4]) )
  #   
  # })
  # output$slhIni <-  renderUI({
  #   sliderInput(inputId = "UIhini", 
  #               label = bornes$noms[5],
  #               min = bornes$minis[5], 
  #               max= bornes$maxis[5], 
  #               value=c(bornes$minis[5],bornes$maxis[5]) )
  #   
  # })
  # output$slslope <-  renderUI({
  #   sliderInput(inputId = "UIslope", 
  #               label = bornes$noms[6],
  #               min = bornes$minis[6], 
  #               max= bornes$maxis[6], 
  #               value=c(bornes$minis[6],bornes$maxis[6]) )
  #   
  # })
  # output$slhMax<-  renderUI({
  #   sliderInput(inputId = "UIhmax", 
  #               label = bornes$noms[7],
  #               min = bornes$minis[7], 
  #               max= bornes$maxis[7], 
  #               value=c(bornes$minis[7],bornes$maxis[7]) )
  #   
  # })
  # 
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
    #                    select = c(gini, moran,densite,coverageRatio )
    #                    
    #                    
    # )
  
    plot_ly(subsetdf, y = ~gini, x = ~moran, z = ~densite, 
            color= ~coverageRatio, colorscale="Viridis",
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



