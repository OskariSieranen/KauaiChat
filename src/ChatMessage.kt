import java.time.LocalDateTime
open class ChatMessage(private val text: String, private val sender: String) {
    private val time = LocalDateTime.now()

    override fun toString(): String {
        return "$sender@$time: $text"
//        sender + "@" + time + ": " + text
    }
}