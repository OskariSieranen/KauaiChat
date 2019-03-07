import java.net.ServerSocket

class ChatServer() {
    fun serve() {
        val ss = ServerSocket(55555)
        println("Port: $ss")
        ChatConsole()
        while (true) {
            try {
                val s = ss.accept()
                val t = Thread(CommandInterpreter(s.getInputStream(), s.getOutputStream()))
                t.start()
            } catch (e: Exception) {
                println("Got exception: $(e.message)")
            } finally {
                println("Done.")
           }
        }
    }
}