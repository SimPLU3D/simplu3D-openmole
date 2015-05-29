import java.{lang, util}

import fr.ign.cogit.geoxygene.api.spatial.geomroot.IGeometry
import fr.ign.cogit.simplu3d.io.load.application.LoaderSHP
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.optimizer.classconstrained.OptimisedBuildingsCuboidFinalDirectRejection
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.predicate.SamplePredicate
import fr.ign.cogit.simplu3d.model.application.{BasicPropertyUnit, Environnement}
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.geometry.impl.Cuboid
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.sampler.GreenSamplerBlockTemperature
import fr.ign.mpp.configuration.{BirthDeathModification, GraphConfiguration}
import fr.ign.mpp.kernel.ObjectBuilder
import fr.ign.parameters.Parameters
import fr.ign.rjmcmc.configuration.ConfigurationModificationPredicate
import fr.ign.simulatedannealing.temperature.{SimpleTemperature, Temperature}
import org.apache.commons.math3.random.{RandomGenerator, MersenneTwister}
import org.codehaus.groovy.ant.GroovycTask
import org.openmole.core.workflow.data._
import org.openmole.core.workflow.job.MoleJob
import org.openmole.core.workflow.sampling.Factor
import org.openmole.core.workflow.task.{ExplorationTask, EmptyTask, Task}
import org.openmole.plugin.task.scala.ScalaTask
import Array._
import java.util.ArrayList
import fr.ign._

import scala.concurrent.duration._



object Test {

  def main(args : Array[String]) = {


    val seed = 42

    var  rand : RandomGenerator = new MersenneTwister(seed)

    val intDuration = 1000



    var folderData = getClass.getResource("data/")
    var paramPath =  getClass.getResource("scenario/building_parameters_project_expthese_3.xml")


    //On charge l'environnement géographique
    var load = new LoaderSHP
    var env = load.getEnvironnement(folderData.getPath)
    var  p: Parameters = env.loadParameters(paramPath.getPath)




    //On récupère une parcelle sur laquelle simuler
    val bPU = env.getBpU().get(0);

    ////////////////////////On prépare le prédicat

    //Valeurs de règles à saisir
    //Recul par rapport à la voirie
    val distReculVoirie : Double = 0.0;
    //Recul par rapport au fond de la parcelle
    val distReculFond : Double = 2;
    //Recul par rapport aux bordures latérales
    val distReculLat : Double = 4;
    //Distance entre 2 boîtes d'une même parcelle
    val distanceInterBati : Double = 5;
    //CES maximal (2 ça ne sert à rien)
    val maximalCES : Double = 2;

    //Instanciation du prédicat
    val pred : SamplePredicate[Cuboid, GraphConfiguration[Cuboid], BirthDeathModification[Cuboid]] = new  SamplePredicate(bPU, distReculVoirie, distReculFond, distReculLat, distanceInterBati,  maximalCES)
    ////////////////////

    var oCB = new OptimisedBuildingsCuboidFinalDirectRejection();

    //Instanciation de la configuration initiale
    var initialGraphConfiguration = oCB.create_configuration(p, bPU.generateGeom().buffer(1), bPU ) //Penser à déployer

    //Il faut instancier ça
    var sampler = oCB.create_sampler(rand, p, bPU, pred);//Penser à déployer

    //Je fais quoi de cette instance de simulconfiguration pour qu'elle initialise les prototype ?
    var simulConf = new SimulConfiguration(initialGraphConfiguration, sampler, 0, rand, new SimpleTemperature(0.0))

    //Idem pour cette instance de tabarchive
    var initTabArchive = new DefaultTabArchive(50,10, 50 , 1)


    val prototypeSimulConf = Prototype[SimulConfiguration]("simulconf")
    val prototypeTabArchive = Prototype[AbstractTabArchive]("TabArchive")

    var taskSimplu3D: Simplu3DTask  = new Simplu3DTask( prototypeSimulConf, Duration(intDuration, MILLISECONDS))




    var mergeTask = new MergeTask(prototypeSimulConf, prototypeTabArchive)








  }
}








