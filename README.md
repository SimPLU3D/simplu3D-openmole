# Simplu3D-OpenMole
============

This projects contains codes to allow distribution and exploration of [SimPLU3D](https://github.com/IGNF/simplu3D) model thanks to [OpenMole](https://www.openmole.org/) library.

Conditions for use
---------------------
This software is free to use under CeCILL license. However, if you use this library in a research paper, you are kindly requested to acknowledge the use of this software.

Furthermore, we are interested in every feedbacks about this library if you find it useful, if you want to contribute or if you have some suggestions to improve it.


Distribution method
---------------------
The zonepacker script allow to split parcels into homogeneous groups in term of number of simulation for calculation distribution with OpenMOLE. In order to run this script, an OSGI bundle have to be built by using *sbt osgiBundle* command from the root of the projet.

The resulting bundle has to be placed in the same directory than the script and can be run by updating the reference to the different folders.
 ./zonepacker.sh "/home/mbrasebin/Bureau/parcels_rulez/real/parcels_rulez.shp" "/tmp/tmp/" "/tmp/out/" 20

The folder produced by this process can be used to be distributed with OpenMole, a template with the script is available in the release of this project. You only have to paste the folders in a subfolder of dataBasicSimu folder and to add the osgi bundle as an OpenMole plugin.


Exploration method
---------------------
Using the Pattern Search Exploration (PSE) method allows the production of diversity of simulation models outputs. This methods is applied on SimPLU3D model in order to generate a variety of built configurations according to external measure (density, distribution of height, etc.).


[More information, implementation and results are provided in the dedicated branch (results_pse)](https://github.com/IGNF/simplu3D-openmole/tree/results_pse)

Contact for feedbacks
---------------------
* [Mickaël Brasebin](http://recherche.ign.fr/labos/cogit/cv.php?nom=Brasebin)
* [Julien Perret](http://recherche.ign.fr/labos/cogit/cv.php?prenom=Julien&nom=Perret)
* [Paul Chapron](https://scholar.google.com/citations?user=EHGHwR8AAAAJ&hl=en)

Acknowledgments
---------------------
+ Paul Chapron for the adaptation of the PSE method
+ [ISC-PIF](https://iscpif.fr/), and notably Romain Reuillon, for their active support for the use of OpenMole.
