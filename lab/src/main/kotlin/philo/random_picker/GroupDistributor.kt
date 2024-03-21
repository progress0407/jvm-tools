package philo.random_picker

import org.springframework.stereotype.Component

/**
 * 전체 인원을 정해진 수의 그룹으로 분배합니다.
 * 가능한 한 균등하게 그룹을 나눕니다.
 * 정확한 분할이 불가능한 경우, 일부 그룹은 다른 그룹보다 약간 더 많은 인원을 수용하여 남은 사람들을 배치합니다.
 *
 * 예시:
 * 10명을 3개의 그룹으로 나누고, 그룹 당 최소 인원 수가 2명이라면,
 * 이 메서드는 각 그룹의 인원 수를 나타내는 [4, 3, 3]을 반환합니다.
 */
@Component
class GroupDistributor {

    fun divideIntoGroups(totalPeopleNumber: Int, groupNumber: Int, minimumGroupSize: Int): MutableList<Int> {

        validateDividable(totalPeopleNumber, groupNumber, minimumGroupSize)

        if (isSimpleCase(totalPeopleNumber, groupNumber)) {
            val groupSize = totalPeopleNumber / groupNumber
            return MutableList(groupNumber) { groupSize }
        }

        val smaller = totalPeopleNumber / groupNumber
        val larger = smaller + 1

        val largerCnt = totalPeopleNumber % groupNumber
        val smallerCnt = groupNumber - largerCnt

        return (MutableList(largerCnt) { larger } + MutableList(smallerCnt) { smaller }).toMutableList()
    }

    /**
     * 전체 인원 수를 그룹으로 균등하게 나눌 수 있는지 확인합니다.
     *
     * 예를 들어,
     * - 전체 인원: 9명
     * - 그룹 수: 3
     * - 그룹 당 최소 인원: 2명
     * - 9 % 3 == 0 이고 9 >= 3 * 2 이므로 조건은 참입니다.
     */
    private fun isSimpleCase(totalPeopleNumber: Int, groupNumber: Int): Boolean {

        return totalPeopleNumber % groupNumber == 0
    }

    private fun validateDividable(peopleNumber: Int, groupNumber: Int, minimumPeopleSize: Int) {

        if (peopleNumber < groupNumber * minimumPeopleSize) {
            throw InsufficientPeopleNumberException()
        }
    }
}
