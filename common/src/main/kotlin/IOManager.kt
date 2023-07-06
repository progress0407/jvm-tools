import mu.KLogger
import mu.KotlinLogging
import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.nio.file.Files
import java.nio.file.Path

abstract class IOManager {

    protected val log: KLogger = KotlinLogging.logger {}

    protected fun createPathIfNotExist(fullPath: Path) {
        Files.createDirectories(fullPath.parent) // Direcotry의 경우 이미 존재하더라도 예외 발생 X
    }

    protected fun writeFile(fullPath: Path, dto: Any) {
        ObjectOutputStream(FileOutputStream(fullPath.toFile())).use { it.writeObject(dto) }
    }
}