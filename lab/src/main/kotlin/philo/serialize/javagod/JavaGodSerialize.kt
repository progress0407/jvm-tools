package philo.serialize.javagod

import kotlin.io.path.Path
import kotlin.io.path.absolute

/**
 * 자바의 신 Serialize 실습
 */
class JavaGodSerialize

fun main() {
    val fileManger = FileManager()
    val serialDTO = SerialDTO("해리포터", 1, true, 1000)
    val pathToSave = getPathToSave()

//    fileManger.saveObject(pathToSave, serialDTO)
    fileManger.loadObject(pathToSave)
}

private fun getPathToSave() = Path("lab", "temp", "serialDTO.txt").absolute()



