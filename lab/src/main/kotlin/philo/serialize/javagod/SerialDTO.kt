package philo.serialize.javagod

import java.io.Serializable

class SerialDTO(
    @Transient private val bookId: String,
    private val bookName: String,
    private val bookOrder: Int,
    private val bestSeller: Boolean,
    private val soldPerDay: Long
) : Serializable {

    companion object {
        private const val serialVersionUID = 1L
    }


    override fun toString(): String {
        return "SerialDTO{" +
                "bookName='" + bookName + '\'' +
                ", bookOrder=" + bookOrder +
                ", bestSeller=" + bestSeller +
                ", soldPerDay=" + soldPerDay +
                '}'
    }
}