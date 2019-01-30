import java.util.ArrayList

object ChatHistory {
    val history = mutableListOf<ChatMessage>()

    fun insert(message: ChatMessage) {
        history.add(message)
        var historySize = history.size
        if (historySize > 10) history.dropLast(1)
    }

    override fun toString(): String {
        var historySize = history.size
        var indexToPrint = 0
        for (index in history) {
            println(index)
        }
        return "Ok"
    }
}