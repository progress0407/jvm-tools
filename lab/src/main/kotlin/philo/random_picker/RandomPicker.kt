package philo.random_picker

import java.io.BufferedReader
import java.io.InputStreamReader

class RandomPicker {

    private val groupDistributor = GroupDistributor();

    fun run() {
        println(
            """
        사람들 정보를 입력해주세요. 균등하게 나누어줍니다.
        e.g. 성우, 필즈
        """.trimIndent()
        )

        val br = BufferedReader(InputStreamReader(System.`in`))
        val people = br.readLine().split(" ").toMutableList()

        println(people)
        println(
            """
            그룹의 갯수와 최소 인원수를 정해주세요.
            e.g. 4 2
        """.trimIndent()
        )
        val (groupNumber, minimumGroupSize) = br.readLine().split(" ").map { it.toInt() }

        val shuffledPeopleGroups = shuffleUserAndGetNew(people, groupNumber, minimumGroupSize)

        for (shuffledPeopleGroup in shuffledPeopleGroups) {
            println(shuffledPeopleGroup)
        }
    }

    private fun shuffleUserAndGetNew(
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
        val finalShuffledPeopleGroups: MutableList<List<String>> = ArrayList<List<String>>()
        for (groupSize in dividedNumber) {
            val peopleSubGroup = ArrayList<String>()
            for (i in 0 until groupSize) {
                val poppedPerson = people.removeAt(0)
                peopleSubGroup.add(poppedPerson)
            }
            finalShuffledPeopleGroups.add(peopleSubGroup)
        }
        return finalShuffledPeopleGroups
    }
}


fun main() {
    val randomPicker = RandomPicker()
    randomPicker.run()
}

