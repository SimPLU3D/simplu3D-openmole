package simplu3dopenmoleplugin

import java.io.File
import fr.ign.cogit.simplu3d.experiments.openmole._

object DistribTask {
  def apply(folderIn: File, folderOut: File, paramFile: File, seed: Long): (Boolean, File) = {
    val res = IAUIDFTask.run(folderIn, folderOut, paramFile, seed);
    (res, folderOut)
  }

}

