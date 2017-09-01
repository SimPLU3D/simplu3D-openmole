---
title: PSE et SimPLU
output: 
  html_document: 
    keep_md: yes
    number_sections: yes
    theme: spacelab
    toc: yes
---






# Pré requis

- Une version fonctionnelle d'openMole avec les credentials pour taper dans la grille EGI .

- une installation de geoxygene dans Eclipse pour pouvoir modifier un peu du code de SimPLU (les fichiers SHPWriter et les fichiers de mesures de morphologies) et le recompiler pour en obtenir un jar qu'on donne à openMOLE

- `Rstudio` avec les librairies déclarées au début du fichier server.R :
```R
library(shiny)
library(plotly)
library(maptools)
library(scales)
library(RColorBrewer)
library(dplyr)
```



# Le workflow principal

Il s'agit d'appliquer la methode PSE (Pattern Space Exploration) d'openMOLE sur SIMPLU.

PSE échantillonne l'espace d'entrée de SIMPLU de façon à maximiser la diversité des configurations obtenues en sortie, dans un espace de mesures choisies par l'utilisateur.


## Vue d'ensemble

Le processus se découpe en trois étapes

1. PSE trouve les configurations possibles et produit des fichiers `populationXXXXX.csv`

2. Répliquer la génération de bâtiments avec les paramètres des configurations trouvées par PSE, et conserver les fichier SHP de ces configurations

3. Matcher les résultats de PSE avec les SHP produites pour constituer les data que le visualisateur affiche.



# Étape 1: PSE

PSE est une méthode qui cherche des points dans un espace de sortie (outputs) en maximisant le nombre et la diversité de ces points.
Pour chaque point (i.e. combinaison d'outputs) trouvé, on dispose des paramètres (inputs) qui ont mené à ce point.
L'ensemble de ces points est mis dans un fichier csv `populationXXXXX.csv` où XXXX représente l'itération de la méthode.

C'est une méthode open-ended, elle s'arrête après un certain nombre d'itération donné en paramètre (ici 400000).

<img src="https://rawgit.com/IGNF/simplu3D-openmole/results_pse/HOWTO_PSE_Simplu/Etape1_PSE.svg" />





## Explication du script openMOLE de PSE

Le script openMOLE se trouve dans le fichier  `pse_Simplu.oms`

Il commence par un certain nombre de déclarations : répertoire où se trouvent les entrées et les sorties de l'exécution d'openMOLE (sur la machine qui le fait tourner)

```scala
import simplu3dopenmoleplugin._

val inputFolder = Val[File]
val paramFile = Val[File]
val outputFolder = Val[File]
```

puis les paramètres d'entrée de SIMPLU sont déclarés :

```scala
val distReculVoirie = Val[Double]
val distReculFond = Val[Double]
val distReculLat = Val[Double]
val maximalCES = Val[Double]
val hIniRoad = Val[Double]
val slopeRoad = Val[Double]
val hauteurMax = Val[Double]
val seed = Val[Long]
```

puis les mesures (=sorties) de SIMPLU

```scala
val energy = Val[Double]
val coverageRatio = Val[Double]
val gini = Val[Double]
val moran = Val[Double]
val entropy = Val[Double]
val boxCount = Val[Double]
val maxHeight = Val[Double]
val densite = Val[Double]
```

La ligne ci-dessous crée la liste `modelOutputs` qui est en fait la sortie de SIMPLU du *point de vue d'openMOLE*. C'est ce qui sera écrit dans les ligne du fichier `populationXXXXX.csv`.
On veut donc bien les paramètres d'entrée de SIMPLU : `distReculVoirie, distReculFond ,distReculLat, maximalCES , hIniRoad , slopeRoad ,hauteurMax, seed`
ainsi que toutes les sorties que SIMPLU produit pour le moment : `energy, coverageRatio, gini,moran,entropy,boxCount,maxHeight,densite`

```scala
def modelOutputs = Seq(distReculVoirie, distReculFond ,distReculLat, maximalCES ,
  hIniRoad , slopeRoad ,hauteurMax, seed, energy, coverageRatio,
   gini,moran,entropy,boxCount,maxHeight,densite)
```


L'objet `model` (une tâche openMOLE ScalaTask) est créé de façon à embarquer l'exécution de SIMPLU et de son contexte pour le distribuer.


```scala
val model =
  ScalaTask("""
    |val (energy, coverageRatio,gini,moran,entropy,boxCount,maxHeight,densite , outputFolder) =
    |   simplu3dopenmoleplugin.Simplu3DTask(inputFolder, newDir(), paramFile,  distReculVoirie, distReculFond, distReculLat, maximalCES, hIniRoad, slopeRoad, hauteurMax, seed)
    |""".stripMargin) set (
  plugins += pluginsOf(Simplu3DTask),
      inputs += (
      inputFolder,
      paramFile,  
      distReculVoirie,
      distReculFond,
      distReculLat,
      maximalCES,
      hIniRoad,
      slopeRoad,
      hauteurMax,
      seed),
    outputs += outputFolder,
    outputs += (modelOutputs: _*),
    inputFolder := workDirectory / "data",
    paramFile := workDirectory / "recuit_normal.xml"
  )
```

Ici , On déclare :

- la tâche elle-même : Simplu3DTask
- les inputs **du point de vue de SimPLU** : un repertoire où lire les entrées, un fichier de zone/parcelle et un fichier de paramètre xml pour l'algo de recuit, et les paramètres des règles. 
- les outputs **du point de vu d'openMOLE**, définis plus haut : c'est ce qu'openMOLE récupère d'une exécution du modèle.
- les répertoires d'input  et d'output sur la machine sur laquelle on a lancé openMOLE (ici le serveur zebulon de l'ISC) ON trouve les fichier `.shp` de la parcelle dans l'inputFolder. L'outputFolder devrait normalement s'appeller `pse` et se trouver dans le workingdirectory (à confirmer)
- le fichier de paramètres du recuit : `recuit_normal.xml` se trouve au niveau du workingDirectory


`Simplu3DTask` est un petit bout de code scala (trouvable sur le repository `simplu3D-openmole/src/main/scala/simplu3dopenmoleplugin/Simplu3DTask.scala`)
qui définit une tâche openMOLE particulière :

```scala
object Simplu3DTask {
  def apply(inputFolder: File, outputFolder: File, paramFile: File,
    distReculVoirie: Double, distReculFond: Double,
    distReculLat: Double, maximalCES: Double, hIniRoad: Double,
    slopeRoad: Double, hauteurMax: Double, seed: Long): (Double, Double, Double, Double, Double, Double, Double, Double, Double, File) = {
    val res = RunTask.run2(inputFolder, outputFolder, paramFile,
      distReculVoirie, distReculFond,
      distReculLat, maximalCES, hIniRoad,
      slopeRoad, hauteurMax, seed)
    (res.energyTot, res.coverageRatio, res.gini, res.moran, res.entropy, res.boxCount, res.maxHeight, res.densite, res.profileMoran, outputFolder)
  }
```

Le résultat de cette tâche est celui de la fonction `run2` dont le code se trouve dans le repository `simplu3D/src/main/java/fr/ign/cogit/simplu3d/experiments/openmole/RunTask.java`
(branche `refactoring reader`)

C'est là qu'on décrit la véritable interface entre la tâche openMOLE et SIMPLU. Tout changement de proptotype de la fonction côté SimPLU  (dans RunTask.java)  par exemple pour ajouter ou retrancher une sortie doit être propagée en éditant le fichier Simplu3DTask.

### Entrées / Sorties côté SIMPLU 

```java
public static TaskExploPSE run2(File folder, File folderOut, File parameterFile,
      double distReculVoirie, double distReculFond, double distReculLat,
      double maximalCES, double hIniRoad, double slopeRoad, double hauteurMax,
      long seed) throws Exception{}
```
Le type de retour `TaskExploPSE` est le suivant :
```java
public  double energyTot;
 public double coverageRatio;
 public double gini;
 public double moran;
 public double entropy;
 public double boxCount;
 public double maxHeight;
 public double densite;
 public double profileMoran ;
```
et il devrait lui aussi être modifié en cas d'ajout/soustraction de mesures de sortie de SimPLU



C'est une exécution "silencieuse" de SIMPLU (sans GUI) qui calcule des mesures sur la configuration bâtie une fois qu'elle est générée.

Le code se trouve dans la classe RunTask qui se trouve dans la branche `refactoring_reader` de simplu3D.

`/simplu3D/src/main/java/fr/ign/cogit/simplu3d/experiments/openmole/RunTask.java`


Si on voulait ajouter des mesures à la sortie de Simplu, il faudrait modifier cette fonction, regénérer le plugin simplu3D, le recharger dans openMOLE et relancer l'exploration.



La méthode PSE est appellée par ce code :
```scala

val pse =
  PSE(
    genome = Seq(
      distReculVoirie in (0.0, 10.0),
      distReculFond in (0.0, 10.0),
      distReculLat in (0.0, 5.0),
      maximalCES in (0.3, 1.0),
      hIniRoad in (0.0, 15.0),
      slopeRoad in (0.5, 3.0),
      hauteurMax in (6.0, 24.0)),
    objectives =  Seq(
      gini in (0.0 to 1.0 by 0.1),
      moran in (-0.2 to 0.5 by 0.01),
      densite in (1.0 to 8.0 by 0.2   ),
      coverageRatio in ( 0.0 to 1.0 by 0.1)  
      ),
    replication = Replication(seed = seed)
 )

val evolution =
  SteadyStateEvolution(
    algorithm = pse,
    evaluation = model,
    parallelism = 4000,
    termination = 400000
  )
```

 `genome` est la liste des entrées que PSE va faire varier lorsqu'il cherche à maximiser la diversité des sorties. 

`objective` est la liste des dimensions  qui forment l'espace que PSE doit couvrir au mieux (les mesures de l'espace de mesures).

La tâche `evolution` est la tâche d'exploration de PSE. C'est une tâche d'un type spécifique ( SteadyStateEvolution) : il s'agit d'exécuter 400000 fois la méthode pse, en demandant de soumettre l'exécution de SIMPLU à 4000 nœuds sur la grille.
A chaque itération de la méthode, un fichier populationXXXX.csv est généré. On ne considère que le plus récent de ces fichiers, les autres sont des résultats intermédiaires moins intéressants à explorer puisque la diversité n'a pas encore été explorée au maximum.

Ici, ce qui fait office de méthode d'évaluation est la tâche `model` elle-même (ses sorties).



Cette ligne déclare l'environnement de la grille EGI sur la virtual organization "complex systems"
```scala
 env = EGIEnvironment("vo.complex-systems.eu")
```


Cette ligne indique qu'il faut sauvegarder la sortie de la fonction/tâche  `evolution` dans le sous-repertoire `pse/`

``` scala
// Define a hook to save the Pareto frontier
val savePopulationHook = SavePopulationHook(evolution, workDirectory / "pse")
```

Cette ligne est la plus importante de toutes, c'est le workflow en lui même :

Le hook qui sauve les résultats dans le fichier est accroché à la tâche `evolution` et s'exécute sur l'environnement `env`.

```scala
// Plug everything together to create the workflow
(evolution hook savePopulationHook on env)
```




# Étape 2: Réplication et génération des configuration résultats
<img src="https://rawgit.com/IGNF/simplu3D-openmole/results_pse/HOWTO_PSE_Simplu/Etape2_CSVSampling.svg" />


## Pourquoi répliquer ? 

Si SimPLU n'est pas déterministe, il est possible que pour un jeu de paramètres donné en entrée , des configurations différentes peuvent être produites d'une éxécution à l'autre. 

Expérimentalement , ça arrive. J'ai fait des tests de replications pour observer la distributions des valeurs de mesures, et regarder si la déviation/variance n'était pas trop importante. Dans le cas que j'ai testé, j'ai trouvé que ça pouvait aller (c'est pas très scientifique mais bon), mais l'étude de cette variance est un défi en soi : dans quelles conditions simPLU produit des résultats susceptibles de dévier significativement d'une réplication à l'autre ? 


Dans les lignes du fichier résultats de PSE, les valeurs des sorties **sont en fait la valeur médiane des mesures** calculée sur le nombre d'évaluation de la configuration.

Une ligne du fichier de PSE = un couple (image, antécédent)  = un paramétrage `p` et des valeurs de mesures `m` et un nombre `s`  (la colonne s'intitule generation$samples) qui indique le nombre de fois que le paramétrage `p` a été simulé.


`m` est la médiane des mesures, calculée sur la population de taille `s`, et mise à jour à chaque itération de la méthode (donc les valeurs changent dans chaque fichier `populationXXXX.csv`)


Dans le cas général de l'application de la méthode PSE, il ne faudrait considérer parmmis les lignes du fichiers résultat, que les points (donc les lignes) dont le nombre d'évalulation (`s`) soit significatif (arbitrairement 100, en fenètre glissante : les 100 derniers points trouvés pour la ligne courrante).
Ceci de façon à ce que la médiane soit un bon estimateur de la valeurs des mesures sur les configurations que produit le paramétrage  `p`.


**Je n'ai pas filtré les resultats de PSE** selon cette règle. Dans notre cas, la confiance dans la configuration obtenue n'était pas primordiale, par rapport au besoin d'exhiber des configurations variées.
PSE discrétise l'espace de sortie, pour nous il est plus important de remplir les cases de l'espace de mesures discrétisé et de comparer les configurations (des cases) les unes aux autres , que de déterminer si un point devrait se trouver dans une case plutôt que dans celle d'à côté.


Il ne s'agit pas d'oublier de répliquer, mais de reporter cette réplication nécessaire, lorsqu'on se concentrera sur une ou deux configurations (point resultat), où là il faudra bien regarder si la stochasticité de SimPLU pose problème et comment le gérer.




## Il faut de toute façon répliquer (au moins une fois)

Rappel : à l'époque de l'expérimentation , on n'arrivait pas à obtenir la génération des fichiers .SHP lors de l'éxécution sur grille. 

On devait donc répliquer la simulation pour chaque ligne du résultat de PSE en local pour obtenir les fichiers SHP. 

Rien n'empèche de répliquer pluseirus fois la générations, de façon à obtenir , pour chaque point de l'espace de mesures plusieurs configurations (dont les mesures seront proches -pour autant que la médiane soit un bon estimateur- de la valeur trouvée par PSE)


## Générer des fichiers SHP 

A l'époque de la première exploration PSE entreprise avec SIMPLU il n'était pas possible d'obtenir directement le fichier SHP correspondant à la configuration bâtie que SIMPLU générait (problème de packaging du code, résolu depuis par Imran).

Ce n'est pas plus mal puisque ça réduit le temps d'exécution de SIMPLU et ça réduit les entrées sorties à de l'écriture de ligne dans un fichier CSV.

L'inconvénient c'est qu'une fois que PSE est terminée , les résultats ne sont donnés que sous la forme d'un fichier csv(` populationXXXXX.csv`) dont les première colonnes correspondent aux paramètres et les dernières aux mesures de sortie de SIMPLU. 
Il faut donc re-générer les fichiers SHP à partir des paramètres d'entrée de ce fichier.

C'est ce que fait le script suivant , avec un `CSVSampling`, qui est une tâche openMOLE spécialement conçue pour échantillonner les lignes d'un fichier. 

## Script de réplication

N.B. Ce script était utilisé **localement**, sur ma machine fixe de l'IGN, car cette étape prenait moins un temps raisonnable (un week end maximum). Il faudrait un peu le modifier pour l'éxécuter sur la grille aussi, de façon à tout faire en distribué et tout récupérer à la fin (idéalement) pour visualiser.

TODO insérer script csvsampling 


Le script appelle SIMPLU sur chacune des lignes du fichier `populationXXXXX.csv` et place récupère les fichiers SHP dans un repertoire.

# Etape 3: Matcher fichiers SHP et resultats PSE et visualiser. 
<img src="https://rawgit.com/IGNF/simplu3D-openmole/results_pse/HOWTO_PSE_Simplu/Etape3_MatchingVizu.svg" />


La première étape du workflow a produit le fichier `populationXXXXX.csv` qui liste les configurations obtenues.
La seconde étape du workflow produit à partir de ce fichier des fichiers SHP pour visualiser la forme des bâtiments.


Si la seconde étape ne génère pas les configurations des lignes du fichier `populationXXXXX.csv` dans l'ordre **exact** des lignes, il faudra faire une mise en correspondance entre les paramètres d'entrée et les fichiers SHP générés.

(Si on imagine qu'on délègue également la génération de fichiers SHP à OpenMOLE, il faut imaginer aussi qu'on recevra les configurations dans le désordre, et il faudra de toute façon gérer ce matching à posteriori)

## Utilisation du code de visualisation des résultats.

Pour fonctionner, le code des fichiers `server.R` et `client.R` du repository `simplu3D-openmole/visuPSE/`
(branche results_pse)  a besoin de deux éléments : 
- un répertoire qui contient les fichiers SHP (et autres .dbf, .shx et .prj .fix ) de chacune des lignes du fichier `populationXXXXX.csv`
- le fichier `populationXXXXX.csv` produit par PSE.
- le fichier `energy.csv` produit par SIMPLU après la génération de .


## Détail : matching des données PSE / bâtiments générés. 


Le matching se fait de façon très simple en concaténant les valeurs des colonnes que les deux fichiers csv ont en commun.

On se retrouve avec deux tableaux (dataframes) de données. L'un provient de PSE (`dfpse`) l'autre provient de la générations avec SIMPLU des configurations découvertes par PSE (`dfsimu`)

On fait le matching en détectant les tuples en commun dans les deux tableaux.


```R
# noms de colonnes en commun entre les deux fichiers (dfsimu et dfpse)
colsEnCommun <- intersect(names(dfsimu), names(dfpse))
#parmi les colonnes communes, on prend que les colonnes des antécédents 
colsEnCommun <- colsEnCommun[1:7]

# on restreint les deux tableaux à ces colonnes
lignesPSE <- dfpse[,colsEnCommun]
lignesSimu <- dfsimu[,colsEnCommun]


# on fait un hash en concaténant les colonnes 
lignesSimu$hash <- apply(lignesSimu, MARGIN = 1 , paste0, collapse="" )
lignesPSE$hash <- apply(lignesPSE, MARGIN = 1 , paste0, collapse="" )


# fonction retourne l'index de la ligne du dataframe dfsimu qui correspond à la list de carac en paramètres
matchingPSESimu <- function(hash){
  return(match(hash, lignesSimu$hash))
}

# idx des lignes de dfsimu correspondant aux lignes de dfpse
idxSimu <- matchingPSESimu(lignesPSE$hash)


# on crée un attribut shppath pour stocker le path de la figure qui matche sur la ligne
dfpse$shpPath <- paste("PSEshp/run_",(dfsimu$seed[idxSimu]),"out.shp", sep="")


```


# Workflow alternatif

L'étape 2 de réplications des configurations que PSE sert à deux choses :

- vérifier/estimer l'effet de la stochasticité de SIMPLU
- "rattraper"  le fait que PSE, initialement, doit évaluer 100 (nombre arbitraire) fois chaque point de l'espace image, et aurait besoin pour cela d'un énorme temps de calcul, lorsque le paramétrage du recuit est le plus fin. On ne va pas le faire pour tous les points, mais on pourrait le faire pour une partie.

## Répliquer certaines des configurations sur grille pour gagner en confiance sur les résultats

Ce workflow alternatif n'a pas été implémenté , mais il ne manque pas grand chose pour l'obtenir : un peu de code Scala à intercaler dans le fichier `.oms`


Après PSE , selectionner dans le fichier `populationXXXXX.csv` un sous ensemble de configurations (des lignes du fichier) => Un peu de code scala est necessaire pour filtrer ces lignes suivant le critère retenu.

Pour ces lignes, extraire les jeu de paramètres, et lancer une exploration qui réplique l'exécution de SIMPLU pour ces valeurs de paramètres (en changeant seulement la seed d'une éxécution à l'autre).

Observer les déviations des mesures  (un peu de code scala est nécessaire pour calculer les stats sur les mesures) par exemple à l'aide de quantiles et d'un seuil.


On obtient alors un sous ensemble de résultats de type PSE (les points dans l'espace de mesures), mais pour lesquels on disposera de plusieurs configurations pour chaque point découvert par PSE, configurations qui justifieront intégralement la valeur des mesures qui définissent le point résultat.

Pour le dire plus simplement , en répliquant n fois, on obtient n configurations par point.





# Mesures additionnelles
Je mets ici toutes les notes que j'ai prises sur les mesures , pour avoir une trace lorsque je reprendrai l'implémentation.

TODO mettre le code java + ref de l'article indice gini moran Tsaï 2005

- Aire de contact entre les bâtiments (Aire verticale)

- Entropie bâtiment (Shannon sur les dimensions)

- Hoover 

- transformée Fourier du signal des hauteurs de façades ()

- Moran circulaire



# Truc à faire pour voir 


- log du temps d'exécutions et du nombre de pas du TDSA



# Code additionel


## Visualiser les distribution de mesures pour des réplications
