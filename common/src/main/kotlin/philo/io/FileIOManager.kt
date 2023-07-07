package philo.io

import philo.log.infoGreen
import java.io.File

class FileIOManager : IOManager<String>() {

    /**
     * 이름을 포함한 경로를 넣는다.
     */
    override fun save(content: String, basePath: String, vararg subPaths: String) {

        val fullPath = convertPath(basePath, *subPaths)
        createPathIfNotExist(fullPath)

        File(fullPath.toUri()).writeText(content)

        log.infoGreen { "File Saved Successfully !! $fullPath" }
    }

    fun load(basePath: String, vararg subPath: String): String {

        val fullPath = convertPath(basePath, *subPath)

        return File(fullPath.toUri()).readText()
    }

    fun delete(basePath: String, vararg subPath: String) {

        val fullPath = convertPath(basePath, *subPath)

        File(fullPath.toUri()).delete()
    }
}