package philo.random_picker

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.assertions.throwables.shouldThrowExactly

class GroupDistributorTest : FunSpec({

    val groupDistributor = GroupDistributor()


    context("그룹 분배 테스트") {

        test("유효하지 않은 경우 - 그룹 생성에 필요한 사람 수가 충분하지 않음") {
            shouldThrowExactly<InsufficientPeopleNumberException> {
                groupDistributor.divideIntoGroups(2, 2, 2)
            }
        }

        context("균등하게 나눌 수 있는 경우") {
            listOf(
                Triple(4, 2, 2) to listOf(2, 2),
                Triple(9, 3, 2) to listOf(3, 3, 3),
                Triple(15, 3, 2) to listOf(5, 5, 5)
            ).forEach { (input, expected) ->
                test("총 인원: ${input.first}, 그룹 수: ${input.second}, 최소 그룹 크기: ${input.third} - 결과: $expected") {
                    val (total, groups, minSize) = input
                    val result = groupDistributor.divideIntoGroups(total, groups, minSize)
                    result shouldContainExactly expected
                }
            }
        }

        context("일반적인 경우") {
            listOf(
                Triple(7, 3, 2) to listOf(3, 2, 2),
                Triple(10, 3, 3) to listOf(4, 3, 3),
                Triple(11, 4, 2) to listOf(3, 3, 3, 2)
            ).forEach { (input, expected) ->
                test("총 인원: ${input.first}, 그룹 수: ${input.second}, 최소 그룹 크기: ${input.third} - 결과: $expected") {
                    val (total, groups, minSize) = input
                    val result = groupDistributor.divideIntoGroups(total, groups, minSize)
                    result shouldContainExactly expected
                }
            }
        }
    }
})