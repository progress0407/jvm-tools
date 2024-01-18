package io.philo

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.philo.io.FileIOManager

class FileIOManagerTest : FunSpec({

    test("파일을 저장하고 읽을 수 있는지 테스트한다") {

        // given
        val fileIOManager = FileIOManager()
        val contentToSave = "Hello World"

        // when
        fileIOManager.save(contentToSave, "temp", "test.txt")
        val contentLoaded = fileIOManager.load("temp", "test.txt")

        // then
        contentLoaded shouldBe contentToSave
        fileIOManager.delete("temp", "test.txt")
    }
})
