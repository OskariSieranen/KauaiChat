import java.lang.StringBuilder
import java.util.*

object ChatHistory: ChatObservable {
    val sb = StringBuilder()
    val history = mutableListOf<ChatMessage>()
    val observers = mutableListOf<ChatObserver>()

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

    override fun registerObserver(observer: ChatObserver) {
        observers.add(observer)
    }

    override fun deregisterObserver(observer: ChatObserver) {
        if (observers.contains(observer)) {
            var i = observers.indexOf(observer)
            observers.removeAt(i)
        }
    }

    override fun notifyObserver(message: ChatMessage) {
    //TODO Loop through all observers -> New message
    }
}