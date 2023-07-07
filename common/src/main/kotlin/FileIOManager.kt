import java.io.File
import java.nio.file.Paths

class FileIOManager : IOManager<String>() {

    /**
     * 이름을 포함한 경로를 넣는다.
     */
    override fun save(content: String, basePath: String, vararg subPath: String) {

        val fullPath = Paths.get(basePath, *subPath)
        createPathIfNotExist(fullPath)

        File(fullPath.toUri()).writeText(content)
    }
}