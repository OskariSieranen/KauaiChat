import java.io.PrintStream
import java.util.*
import java.net.ServerSocket

fun main(args : Array<String>) {
    val ss = ServerSocket(0)
    println("Port: $ss")
    val s = ss.accept()
    val ci = CommandInterpreter(s.getInputStream(), s.getOutputStream())
    ci.run()
}