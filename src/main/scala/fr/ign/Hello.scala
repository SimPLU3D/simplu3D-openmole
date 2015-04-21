package fr.ign

import org.openmole.core.dsl._
import org.openmole.core.workflow.data._
import org.openmole.plugin.task.scala._

val configuration = Val[Configuration]
val energy = Val[Double]
val evaluatedConfiguration = Val[EvaluatedConfiguration]
val evaluatedConfigurationArchive = Val[Array[EvaluatedConfiguration]]

class Simplu3DTask (duration: Duration) extends Task {
  def process(context) = {
    val (finalConfiguration, finalEnergy) = compute(context(configuration), duration)
    Variable(evaluatedConfiguration, EvaluatedConfiguration(finalConfiguration, finalEnergy))
  }
}

case class Configuration ()
case class EvaluatedConfiguration (configuration: Configuration, energy: Double)

class MergeTask (maxPopSize: Int) extends Task {
  def process(context) = {
    val (newEvaluatedConfigurationArchive, newConfiguration) = merge(context(evaluatedConfigurationArchive), context(evaluatedConfiguration))
    Variable(evaluatedConfigurationArchive, newEvaluatedConfigurationArchive) + Variable(configuration, newConfiguration)
  }
}

