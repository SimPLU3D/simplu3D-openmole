package simplu3dopenmoleplugin

import java.io.File
import fr.ign.cogit.simplu3d.experiments.smartplu.simulator._

object SmartPLUTask {
  def apply(folderIn: File, dirName: String, folderOut: File, paramFile: File, seed: Long): (String, File) = {
    val res = SmartPLUTaskRunner.run(folderIn, dirName, folderOut, paramFile, seed);
    (res, folderOut)
  }

}

