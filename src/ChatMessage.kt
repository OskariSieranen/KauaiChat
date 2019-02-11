import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
open class ChatMessage(private val text: String, val sender: String, private val time: LocalDateTime) {

    //private var time = LocalDateTime.now()
    private val format = DateTimeFormatter.ofPattern("HH:mm:ss")
    private val forTime = time.format(format)
    override fun toString(): String {
        return "$sender@$forTime $text"
//        sender + "@" + time + ": " + text
    }
}