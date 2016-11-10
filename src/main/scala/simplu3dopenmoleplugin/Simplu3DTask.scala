package simplu3dopenmoleplugin

import java.io.File
import fr.ign.cogit.simplu3d.experiments.openmole._

// object Simplu3DTask {
//   def apply(inputFolder: File, outputFolder: File, paramFile: File,
//     idBPU: Int, distReculVoirie: Double, distReculFond: Double,
//     distReculLat: Double, maximalCES: Double, hIniRoad: Double,
//     slopeRoad: Double, hauteurMax: Double, seed: Long): (Double, Double, Long, File) = {
//     val res = RunTask.run(inputFolder, outputFolder, paramFile, idBPU, distReculVoirie, distReculFond, distReculLat, maximalCES, hIniRoad, slopeRoad, hauteurMax, seed)
//     (res.energy, res.coverageRatio, res.signature, outputFolder)
//   }
// }

object Simplu3DTask {
  def apply(inputFolder: File, outputFolder: File, paramFile: File,
    distReculVoirie: Double, distReculFond: Double,
    distReculLat: Double, maximalCES: Double, hIniRoad: Double,
    slopeRoad: Double, hauteurMax: Double, seed: Long): (Double, Double, Double, Double, Double, Double, Double, Double, File) = {
    val res = RunTask.run2(inputFolder, outputFolder, paramFile,
      distReculVoirie, distReculFond,
      distReculLat, maximalCES, hIniRoad,
      slopeRoad, hauteurMax, seed)
    (res.energyTot, res.coverageRatio, res.gini, res.moran, res.entropy, res.boxCount, res.maxHeight, res.densite, outputFolder)
  }
}
