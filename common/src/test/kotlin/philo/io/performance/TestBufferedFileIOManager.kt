package philo.io.performance

import philo.io.IOManager
import java.io.File

class TestBufferedFileIOManager : IOManager<String>() {

    override fun save(content: String, basePath: String, vararg subPaths: String) {

        val fullPath = convertPath(basePath, *subPaths)
        createPathIfNotExist(fullPath)

        File(fullPath.toUri()).bufferedWriter().use { it.write(content) }

        log.info { "File Saved Successfully !! $fullPath" }
    }

    fun load(basePath: String, vararg subPath: String): String {

        val fullPath = convertPath(basePath, *subPath)

        return File(fullPath.toUri()).bufferedReader().use { it.readText() }
    }

    fun delete(basePath: String, vararg subPath: String) {

        val fullPath = convertPath(basePath, *subPath)

        File(fullPath.toUri()).delete()
    }
}