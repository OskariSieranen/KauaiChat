import java.net.ServerSocket

class ChatServer() {
    fun serve() {
        // TODO add try catch finally to the server socket thing
        val ss = ServerSocket(0)
        println("Port: $ss")
        while (true) {
            val s = ss.accept()
            val t = Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream()))
            t.start()
        }

    }
}