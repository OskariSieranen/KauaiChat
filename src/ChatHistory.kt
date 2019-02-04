import java.lang.StringBuilder
object ChatHistory {
    val sb = StringBuilder()
    val history = mutableListOf<ChatMessage>()

    fun insert(message: ChatMessage) {
        history.add(message)
        if (history.size > 10) {
            history.removeAt(0)
        }
    }

    override fun toString(): String {
        var toprint = ""
        for (index in history) {
            toprint = sb.append(index).append("\n").toString()
        }
        println(toprint)
        return toprint
    }
}