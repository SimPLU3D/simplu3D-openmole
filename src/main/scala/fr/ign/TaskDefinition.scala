package fr.ign

import java.util.ArrayList

import fr.ign.cogit.geoxygene.api.spatial.geomroot.IGeometry
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.geometry.impl.Cuboid
import fr.ign.cogit.simplu3d.rjmcmc.cuboid.sampler.GreenSamplerBlockTemperature
import fr.ign.mpp.configuration.{BirthDeathModification, GraphConfiguration}
import fr.ign.rjmcmc.sampler.Sampler
import fr.ign.simulatedannealing.temperature.Temperature
import org.apache.commons.math3.random.RandomGenerator
import org.openmole.core.workflow.data._
import org.openmole.core.workflow.task.Task

import scala.Array._
import scala.concurrent.duration.Duration




//Simulconfiguration, l'objet que se transmettent les 2 tasks

class SimulConfiguration(configuration : GraphConfiguration[Cuboid], sampler : Sampler[GraphConfiguration[Cuboid], BirthDeathModification[Cuboid]], energy : Double, e : RandomGenerator,
                         t : Temperature   ){
  var currentConfiguration : GraphConfiguration[Cuboid] = configuration
  var currentSampler : Sampler[GraphConfiguration[Cuboid], BirthDeathModification[Cuboid]] = sampler
  var currentEnergy : Double = currentEnergy
  var randomGenerator :  RandomGenerator = e
  var currentTemperature : Temperature= t
}


/**
 * Classe abstraite permettant de decrire la matrice contenant les configurations et evoluant au fil des iterations
 * Elle est astraite, on peut definir ce que l on veut derrire la definition X et Y, on pourrait faire plus de dimensions plus tard
 */
abstract class AbstractTabArchive(nbValX : Integer,  nbYVal : Integer){
  var currentNBValX : Integer= nbValX;
  var currentNBValY : Integer= nbYVal;

  var configurationMatrix =   ofDim[GraphConfiguration[Cuboid]](currentNBValX,currentNBValY)
  var energyMatrix =   ofDim[Double](currentNBValX,currentNBValY)

  def addValue(energy : Double,  newConfiguration : GraphConfiguration[Cuboid]): Boolean ={

    val x: Integer = getINDX(newConfiguration)
    val y : Integer = getINDY(newConfiguration)

    if( x >= 0 && x < nbValX-1 && y >=0 && y < nbYVal -1 ){

      var oldEnergy = energyMatrix(x)(y)
      var newEnergy = newConfiguration.getEnergy

      if(oldEnergy < newEnergy){
        energyMatrix(x)(y) = newEnergy
        configurationMatrix(x)(y) = newConfiguration
      }

    }


    return true;
  }

  def getINDX(newConfiguration : GraphConfiguration[Cuboid]) : Integer
  def getINDY(newConfiguration : GraphConfiguration[Cuboid]) : Integer

}


//Un instanciation basique de AbstractTabArchive avec le nombre de boîtes et le CES
class DefaultTabArchive( val nbValX : Integer,   val nbYVal : Integer, val valMaxX : Double, val valMaxY : Double) extends AbstractTabArchive (nbValX ,  nbYVal ){

  val currentValMaxX = valMaxX;
  val currentValMaxY = valMaxY;


  override def getINDX(newConfiguration : GraphConfiguration[Cuboid]): Integer = {

    val d1 = newConfiguration.size().toDouble
    val d2 = valMaxX.toDouble

    val int1 = (( d1/d2).floor)

    return int1.toInt

  }

  override def getINDY(newConfiguration : GraphConfiguration[Cuboid]): Integer = {
    val iterator = newConfiguration.iterator()

    val listC = new java.util.ArrayList[Cuboid]()

    while(iterator.hasNext){
      listC.add(iterator.next())
    }

    if(listC.isEmpty){
      return 0
    }

    val nbElem = listC.size();

    var geom : IGeometry = listC.get(0).getFootprint();

    for( i <- 1 to nbElem ){
      geom = geom.union(listC.get(i).getFootprint());
    }

    return (geom.area() / valMaxY).floor.toInt
  }
}


/**
 * On sample : on met à jour le contexte si une nouvelle configuration est proposée
 * @param simulConf
 * @param duration
 */
class Simplu3DTask (val simulConf : Prototype[SimulConfiguration], duration: Duration) extends Task {

  def compute(simulConf: SimulConfiguration, duration: Duration) = {
    simulConf.currentSampler.sample(simulConf.randomGenerator, simulConf.currentConfiguration, simulConf.currentTemperature )
    if(!   simulConf.currentSampler.blockTemperature()){
      simulConf.currentConfiguration = simulConf.currentConfiguration
      simulConf.currentEnergy =  simulConf.currentConfiguration.getEnergy
    }
  }

  override protected def process(context: Context): Context = {
    val newConfiguration =  compute(context(simulConf), duration)
    Variable(simulConf,  newConfiguration )
  }

  override def defaults: DefaultSet = ???

  override def name: String = "Merge Task"

  override def outputs: DataSet = ???

  override def inputs: DataSet = ???
}




//Mise à jour de la tabarchive si on améliore une des configurations
class MergeTask ( val simulConf : Prototype[SimulConfiguration], val matrixArchive : Prototype[AbstractTabArchive] ) extends Task {


  def merge(simulConf : SimulConfiguration,matrixArchive : AbstractTabArchive ) {

    matrixArchive.addValue(simulConf.currentEnergy, simulConf.currentConfiguration)

    return matrixArchive
  }

  override protected def process(context: Context): Context = {
    val  newArchiveTable = merge(context(simulConf), context(matrixArchive) )
    Variable(matrixArchive, newArchiveTable)
  }

  override def defaults: DefaultSet = ???

  override def name: String = "Merge Task"

  override def outputs: DataSet = ???

  override def inputs: DataSet = ???
}

