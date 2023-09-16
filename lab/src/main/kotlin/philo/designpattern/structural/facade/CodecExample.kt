package philo.designpattern.structural.facade

// Classes from the complex 3rd-party video conversion framework

class VideoFile(val filename: String)

interface Codec

class OggCompressionCodec : Codec

class MPEG4CompressionCodec : Codec

object CodecFactory {
    fun extract(file: VideoFile): Codec {
        // Here, you would have logic to determine the codec of the file.
        // For simplicity, let's always return an OggCompressionCodec
        return OggCompressionCodec()
    }
}

object BitrateReader {
    fun read(file: VideoFile, sourceCodec: Codec): ByteArray {
        // Mocked read implementation
        return file.filename.toByteArray()
    }

    fun convert(buffer: ByteArray, destinationCodec: Codec): ByteArray {
        // Mocked convert implementation
        return buffer
    }
}

object AudioMixer {
    fun fix(data: ByteArray): ByteArray {
        // Mocked fix implementation
        return data
    }
}

// Our facade class
class VideoConverter {
    fun convert(filename: String, format: String): ConvertedFile {
        val file = VideoFile(filename)
        val sourceCodec = CodecFactory.extract(file)
        val destinationCodec = if (format == "mp4") {
            MPEG4CompressionCodec()
        } else {
            OggCompressionCodec()
        }
        var buffer = BitrateReader.read(file, sourceCodec)
        buffer = BitrateReader.convert(buffer, destinationCodec)
        buffer = AudioMixer.fix(buffer)
        return ConvertedFile(buffer)
    }
}

class ConvertedFile(private val data: ByteArray) {
    fun save() {
        println("covert files ")
        // Implement save logic here
    }
}

fun main() {
    val converter = VideoConverter()
    val mp4 = converter.convert("funny-cats-video.ogg", "mp4")
    mp4.save()
}