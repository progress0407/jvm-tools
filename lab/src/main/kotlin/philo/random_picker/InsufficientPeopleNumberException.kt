package philo.random_picker

class InsufficientPeopleNumberException() : RuntimeException(EXCEPTION_MESSAGE) {

    companion object {
        const val EXCEPTION_MESSAGE =
            "입력한 사람의 수가 그룹을 만들 만큼 충분히 크지 않습니다."
    }
}
