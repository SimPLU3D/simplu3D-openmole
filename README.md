# Pattern Search Exploration method on SimPLU Model

## Content of this branch 

+ This readme file that gives an introduction on PSE exploration applied to SimPLU 
+ R scripts to analyze results of PSE method
+ instructions to batch-generate shapefiles corresponding to some results' configurations  
+ some results 




## PSE Method 
Pattern Search Exploration (PSE) is an open-ended evolutionary method of the OpenMOLE frameWork, designed to explore the diversity of simulation models outputs. See the article [here](http://journals.plos.org/plosone/article?id=10.1371/journal.pone.0138212)

Whereas optimization techniques focus on discovering (near)optimal solutions from a set of feasible solutions, PSE aims at exhibiting the diversity of patterns a model can produce, by cleverly selecting the parameters that produce patterns that are the most different one to each other as possible.
The massive amounts of simulation runs that PSE requires is made possible by framing the model into the OpenMOLE platform. (See the master branch of this repository)



In other words, PSE make a **clever sampling of the pattern space of a simulation model**.





##Application to SimPLU

SimPLU aims at simulating buildings morphologies w.r.t. formalized urbanistic rules of construction.
Some of these rules are *parametric* e.g. "you shall not build your house within 2 meters of the road" . 

These parameters have a direct influence on the shape of compatible buildings affecting their volumes, heights etc. and are defined at the scale of a *parcel*.

The idea of a PSE framework applied on SimPLU is to explore the diversity of buildings shape at the scale of a *zone* (i.e. several contiguous parcels)



## The SimPLU model

The project is described and available [here](../simplu3D)

For the sake of this particular exploration case, we will abstract SimPLU as a machine that transforms inputs parameters into outputs measure.

The inputs under study are the parameters of the rules constituting urban regulation document (Plan Local d'Urbanisme a.k.a. PLU)
The other inputs (the set of rules at play, the zone where the simulation is performed,etc.) are kept constant.


The outputs under study are global measures (i.e. at the scale of the zone) of the morphologies of buildings and there disposition on the zone. 
Other outputs are dimensions and coordinates of the cubes constituting the buildings in the simulated the zone. We use them later for visualization of some of the configurations discovered by PSE.


## Input space

The input space is constituted of 7 parameters:

+ distReculVoirie: the distance between buildings of a parcel and the road (street)
+ distReculFond: the distance between buildings of a parcel and the bottom of the parcel
+ distReculLat: the distance between buildings of a parcel and the sides of the parcel   
+ maximalCES: the maximal surface ratio  between buildings surface and parcel surface  
+ hIniRoad : the height of the wall facing the road (see figure below)
+ slopeRoad &#2248 the angle of the line between the top of the roof and the plane at hIniRoad (see figure below)
+ hauteurMax : the maximal height of the building


![imputparameters](./readme_images/schema_param_input.png)



An other implicit input is the seed used for the random number generator of SimPLU.


## Output space 


### Desired properties of measures

Various measures are available to qualify the output of a simulation run; PSE method accepts any kind of measures (discrete continuous). The ideal measures for PSE should be : 

+ discriminative regarding the possible output patterns (we are looking for variety)
+ as independent as possible of each other 
+ easily interpretable 
+ sound regarding the thematic domain (here, a mix between urbanism and soil occupation)
 


### Current available measures

