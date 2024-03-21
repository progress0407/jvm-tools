package philo.random_picker

class InsufficientPeopleNumberException() : RuntimeException(EXCEPTION_MESSAGE) {

    companion object {
        val EXCEPTION_MESSAGE =
            "There are not enough people to create groups with the given input."
    }
}
