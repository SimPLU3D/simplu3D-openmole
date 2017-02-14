
# PSE et SimPLU



## Prérequis :

- Une version fonctionnelle d'openMole avec les credentials pour taper dans la grille EGI .

- une installation de geoxygène dans Eclipse pour pouvoir modifier un peu du code de SimPLU (les fichiers SHPWriter et les fichiers de mesures de morphologies) et le recompiler pour en obtenir un jar qu'on donne à openMOLE

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

PSE échantillone l'espace d'entrée de SIMPLU de façon à maximiser la diversité des configurations obtenues en sortie, dans un espace de mesures choisies par l'utilisateur.


## vue d'ensemble

Le processus se découpe en trois étapes

1. PSE trouve les configurations possibles et produit des fichiers `populationXXXXX.csv`

2. Repliquer la génération de bâtiments avec les paramètres des configurations trouvées par PSE, et conserver les fichier SHP de ces configurations

3. Matcher les resultats de PSE avec les SHP produites pour constituer les data que le visualisateur affiche.





# Workflow alternatif

L'étape 2 de réplications des configurations que PSE sert à deux choses :

- vérifier l'effet de la stochasticité de SIMPLU
- "rattraper"  le fait que PSE, initialement, doit évaluer 100 (nombre arbitraire) fois chaque point de l'espace image, et aurait besoin pour cela d'un énorme temps de calcul, lorsque le paramétrage du recuit est le plus fin.


En jouant sur cette étape 2 , on peut transformer le workflow de deux façons différentes

## Economiser la réplication

Si on a de bonnes raisons de penser que SIMPLU, pour un jeu de paramètres `p` et un  îlot urbain `i`  ne dévie pas beaucoup qualitativement (volume, hauteur, etc..), ces réplications peuvent être court-circuitées (ou fortement réduites) (ou exécutées avec un paramétrage plus bourrin)

A la rigueur, dans un cas extrème , on peut se contenter de générer une seule configuration par ligne du fichier `populationXXXXX.csv`.

Il faudra se rappeler qu'il y aura toujours un risque que SIMPLU génère une configuration différente de celle qu'on observe à l'écran.

Un bon moyen de s'autoriser ce court-circuit est de faire une petite étude exploratoire (avec le script d'exploration) préliminaire et d'observer la distribution des mesures.

## Répliquer certaines des configurations sur grille pour gagner en confiance sur les résultats

Ce workflow alternatif n'a PAS ETE FAIT , mais il ne manque pas grand chose pour l'obtenir : un peu de code Scala à intercaler dans le fichier `.oms`


Après PSE , selectionner dans le fichier `populationXXXXX.csv` un sous ensemble de configurations (des lignes du fichier) => Un peu de code scala est necessaire pour filtrer ces lignes suivant le critère retenu.

Pour ces lignes, extraire les jeu de paramètres, et lancer une exploration qui réplique l'exécution de SIMPLU pour ces valeurs de paramètres.

Observer les déviations des mesures  (un peu de code scala est nécessaire pour calculer les stats sur les mesures) par exemple à l'aide de quantiles et d'un seuil.








# Mesures additionnelles
Je mets ici toutes les notes que j'ai prises sur les mesures , pour avoir une trace lorsque je reprendrai l'implémentation.
