package philo.serialize.javagod

import ObjectManager

/**
 * 자바의 신 Serialize 실습
 */
class JavaGodSerialize

fun main() {
    val fileManger = ObjectManager()
    val objectToSave = SerialDTO("secret id 0101", "해리포터", 1, true, 1000)

    fileManger.saveObject(objectToSave, "lab", "temp", "serialDTO.txt")
    fileManger.loadObject("lab", "temp", "serialDTO.txt")
}





