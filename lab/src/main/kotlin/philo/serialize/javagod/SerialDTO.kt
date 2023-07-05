package philo.serialize.javagod

import java.io.Serializable

class SerialDTO(
    private val bookName: String,
    private val bookOrder: Int,
    private val bestSeller: Boolean,
    private val soldPerDay: Long
) : Serializable {

    override fun toString(): String {
        return "SerialDTO{" +
                "bookName='" + bookName + '\'' +
                ", bookOrder=" + bookOrder +
                ", bestSeller=" + bestSeller +
                ", soldPerDay=" + soldPerDay +
                '}'
    }
}