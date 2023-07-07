package philo.io

import java.io.FileInputStream
import java.io.ObjectInputStream
import java.nio.file.Path

class ObjectIOManager : IOManager<Any>() {

    override fun save(content: Any, basePath: String, vararg subPaths: String) {
        try {
            val fullPath = convertPath(basePath, *subPaths)
            createPathIfNotExist(fullPath)
            writeFile(fullPath, content)
            log.info { "save object success fullPath: $fullPath" }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveObject(fullPath: Path, obj: Any) {
        try {
            createPathIfNotExist(fullPath)
            writeFile(fullPath, obj)
            log.info { "save object success fullPath: $fullPath" }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadObject(basePath: String, vararg subPaths: String) {
        try {
            val fullPath = convertPath(basePath, *subPaths)
            val loadObject = ObjectInputStream(FileInputStream(fullPath.toFile())).use { it.readObject() }
            log.info { "load object = $loadObject" }
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
}