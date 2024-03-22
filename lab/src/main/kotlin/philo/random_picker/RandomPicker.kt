package philo.random_picker

import java.io.BufferedReader
import java.io.InputStreamReader


class RandomPicker {

    companion object {
        private const val INPUT_PEOPLE_MESSAGE = """
                그룹을 나눌 사람들의 이름을 입력해주세요. 가능한 한 균등하게 나누어줍니다.
                만일 "END"를 입력하면 종료됩니다.
                e.g. 성우, 필즈
                """

        private const val INPUT_GROUP_NUMBER_AND_MINIMUM_PEOPLE_SIZE_MESAGE = """
                그룹의 갯수와 최소 인원수를 정해주세요.
                e.g. 4 2
                """

        private const val CONTINUE_OR_END_MESSAGE = "계속 진행하려면 아무키나 누르십시오. 만일 END를 입력하면 종료됩니다."

        private const val GROUP_DIVIDED_RESULT_MESSAGE = "그룹 나누기 결과: "

        private const val EXIT_MESSAGE = "END 입력으로 프로그램을 종료합니다."
    }

    private val groupDistributor = GroupDistributor()

    fun run() {
        val br = BufferedReader(InputStreamReader(System.`in`))

        while (true) {
            println(INPUT_PEOPLE_MESSAGE.trimIndent())
            val people = br.readLine().split(" ").toMutableList()

            println(INPUT_GROUP_NUMBER_AND_MINIMUM_PEOPLE_SIZE_MESAGE.trimIndent())
            val (groupNumber, minimumGroupSize) = br.readLine().split(" ").map { it.toInt() }
            val shuffledPeopleGroups =
                shufflePeopleAndMakeGroups(people, groupNumber, minimumGroupSize)

            println(GROUP_DIVIDED_RESULT_MESSAGE)
            printPeopleGroups(shuffledPeopleGroups)

            println(CONTINUE_OR_END_MESSAGE)
            if (br.readLine().uppercase() == "END") {
                println(EXIT_MESSAGE)
                break
            }
        }

        br.close()
    }


    private fun shufflePeopleAndMakeGroups(
        people: MutableList<String>,
        groupNumber: Int,
        minimumGroupSize: Int
    ): List<List<String>> {

        people.shuffle()
        val dividedNumber =
            groupDistributor.divideIntoGroups(people.size, groupNumber, minimumGroupSize)
        return aggregateShuffledUsers(dividedNumber, people)
    }

    /**
     * 유저 집계
     *
     * @return
     */
    private fun aggregateShuffledUsers(
        dividedNumber: List<Int>,
        people: MutableList<String>
    ): List<List<String>> {

        val peopleGroups = ArrayList<List<String>>()

        for (groupSize in dividedNumber) {
            val peopleGroup = ArrayList<String>()
            for (i in 0 until groupSize) {
                val poppedPerson = people.removeAt(0)
                peopleGroup.add(poppedPerson)
            }
            peopleGroups.add(peopleGroup)
        }

        return peopleGroups
    }

    private fun printPeopleGroups(shuffledPeopleGroups: List<List<String>>) {

        for (shuffledPeopleGroup in shuffledPeopleGroups)
            println(shuffledPeopleGroup)
    }
}

fun main() {
    val randomPicker = RandomPicker()
    randomPicker.run()
}
