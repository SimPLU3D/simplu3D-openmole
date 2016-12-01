library(ggplot2)
library(plotly)
library(cowplot) # this is for grid plot layout

  
#path to the results file
path <- "/home/pchapron/dev/result_Simplu"
setwd(path)

#the file itself
en <- read.csv("population400000.csv")



#detection of varying columns 
#in PSE results , everything varies
#in exploration , some of the columns may remain constant (fixed rule of PLU)
for (i in 1:length(en)){
  if (sd(en[,i])==0 && !is.na(en[,i])){
    cat(names(en)[i]," constante\n") 
  } 
  else{         
    cat(names(en)[i]," varie\n")
  }
}


#convenience function for short labels , one deciamal numbers
uneDdecimale <- function(x) {
  as.character(round(x,3))
}





#variance covariance matrix of outputs
# columns number may vary according to what simPLU produces
var(en[,9:12])


#plot every scatterplot column vs. column
plot(en)

#plot only outputs
plot(en[,9:12])


#distributions (outputs)

p2 <- ggplot(en)
p2 <- p2 +geom_histogram(aes(x=coverageRatio), bins=60)
p2

p3 <- ggplot(en)
p3 <- p3 +geom_histogram(aes(x=gini), bins=60)
p3

p4 <- ggplot(en)
p4 <- p4 +geom_histogram(aes(x=moran), bins=60)
p4


p5 <- ggplot(en)
p5 <- p7 +geom_histogram(aes(x=densite), bins=60)
p5

plot_grid(p2,p3,p4,p5)


#bivariate plots exemple

# coverageRatio / energy 
p <- ggplot()
p <- p + geom_point( aes(x=gini ,y=moran, col= densite), alpha=0.8, data=en)
p <- p + scale_x_continuous()
p <- p + scale_color_continuous(guide = "colourbar", label=uneDdecimale)
p


#3D scatterplot exemple 
plot_ly(en, y = ~gini, x = ~moran, z = ~densite, 
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
        
        )


