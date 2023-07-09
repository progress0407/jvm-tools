package philo.serialize.javagod

import philo.io.ObjectIOManager

/**
 * 자바의 신 Serialize 실습
 */
class JavaGodSerialize

fun main() {
    val objectManager = ObjectIOManager()
    val objectToSave = SerialDTO("secret id 0101", "해리포터", 1, true, 1000)

    objectManager.save(objectToSave, "lab", "temp", "serialDTO.txt")
    objectManager.loadObject("lab", "temp", "serialDTO.txt")
}





