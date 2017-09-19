#
# This is the user-interface definition of a Shiny web application. You can
# run the application by clicking 'Run App' above.
#
# Find out more about building applications with Shiny here:
#
#    http://shiny.rstudio.com/
#

library(shiny)
library(plotly)



# Define UI for application that draws a histogram
shinyUI(
  fluidPage(
    # Application title
    titlePanel("SimPLU3D PSE results exploration"),
    
    sidebarLayout(
      sidebarPanel(
       
        sliderInput(
          inputId = "UIreculvoi",
          label = "recul voirie",
          min = 0,
          max = 10,
          value = c(0, 10)
        )
        
        ,
        sliderInput(
          inputId = "UIreculfon",
          label = "recul fond",
          min = 0,
          max = 10,
          value = c(0, 10)
        )
        
        ,
        sliderInput(
          inputId = "UIrecullat",
          label = "recul lat",
          min = 0,
          max = 5,
          value = c(0, 5)
        )
        
        ,
        sliderInput(
          inputId = "UIces",
          label = "max CES",
          min = 0.3,
          max = 1,
          value = c(0.3, 1)
        )
        
        ,
        
        
        
        sliderInput(
          inputId = "UIhini",
          label = "hini Road",
          min = 0,
          max = 15,
          value = c(0, 15)
        )
        
        ,
        sliderInput(
          inputId = "UIslope",
          label = "slopeRoad",
          min = 0.5,
          max = 3,
          value = c(0.5, 3)
        )
        
        ,
        sliderInput(
          inputId = "UIhmax",
          label = "hauteur Max",
          min = 6,
          max = 24,
          value = c(6, 24)
        )
        ,
        sliderInput(
          inputId = "nbpoints",
          label = "nb points",
          min = 100,
          max = 3552,
          value = 1500
        )
        
        ,
        width = 3
      )
      
      ,
      
      # Show a plot of the generated distribution
      mainPanel(
        fluidRow(column(6,
                        plotlyOutput("nuagePlot")),
                 column(6,
                        htmlOutput("widgetQGISThreeJS")))
       ,
       # verbatimTextOutput("event"),
        fluidRow(column(6,
                        imageOutput("imgConfig")),
                 column(6,
                        tableOutput("tabregles")))
       , width=9
         )
    )
  )
)