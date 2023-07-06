import mu.KLogger
import mu.KotlinLogging
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Path

class FileManager {

    private val log: KLogger = KotlinLogging.logger {}

    fun saveObject(fullPath: Path, dto: Any) {
        try {
            createPathIfNotExist(fullPath)
            writeFile(fullPath, dto)
            log.info { "save object success fullPath: $fullPath" }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadObject(fullPath: Path) {
        try {
            val loadObject = ObjectInputStream(FileInputStream(fullPath.toFile())).use { it.readObject() }
            log.info { "load object = $loadObject" }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun createPathIfNotExist(fullPath: Path) {
        Files.createDirectories(fullPath.parent) // Direcotry의 경우 이미 존재하더라도 예외 발생 X
    }

    private fun writeFile(fullPath: Path, dto: Any) {
        ObjectOutputStream(FileOutputStream(fullPath.toFile())).use { it.writeObject(dto) }
    }
}