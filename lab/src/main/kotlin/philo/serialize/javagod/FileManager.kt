package philo.serialize.javagod

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Path

class FileManager {

    fun saveObject(fullPath: Path, dto: SerialDTO) {
        try {
            createPathIfNotExist(fullPath)
            writeFile(fullPath, dto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadObject(fullPath: Path) {
        try {
            val ob = ObjectInputStream(FileInputStream(fullPath.toFile())).use { it.readObject() }
            println("ob = ${ob}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createPathIfNotExist(fullPath: Path) {
        Files.createDirectories(fullPath.parent) // Direcotry의 경우 이미 존재하더라도 예외 발생 X
    }

    private fun writeFile(fullPath: Path, dto: SerialDTO) {
        ObjectOutputStream(FileOutputStream(fullPath.toFile())).use { it.writeObject(dto) }
    }
}