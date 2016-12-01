# Visualisation of PSE results


Prototype of PSE rsults  exploration tool.

PSE exhibit configurations of simPLU outputspace by cleverly selecting inputspace elements.
The typical PSE results consists in a CSV file where first columns are iput parameters and last columns are measures (output) values obtained when the model is simulated with these inputs. 

Three of the outputspace dimensions (measures) are chosen to display these configurations as a 3D scatter plot (a fourth measure is used through a colourscale)

The scatterplot can be filtered by choosing input parameter space bounds.

Hovering (or clicking) a point in the scatterplot display the corresponding configuration picture. 




## Requirements 

### software
+ Rstudio (for `shiny` stuff)
+ `plotly` library

### data
+ PSE results file ("population400000.csv" in the code) 
+ folder of configuration pictures, in the same order as the lines of the PSE file result

## Launch

It's a classic shinyapp from Rstudio.

+ get everything in this folder  
+ put it in a directory
+ open `ui.R` and `server.R` in Rstudio
+ change the path at the top of the `server.R` and replace it by the folder where your files are.
+ launch app (better rendering in a browser)


