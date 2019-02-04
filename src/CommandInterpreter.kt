import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.Scanner
import kotlin.test.currentStackTrace
// TODO newMessage(message:ChatMessage)
class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream): Runnable {
    val sc = Scanner(inputStream)
    val pr = PrintStream(outputStream)

    override fun run() {
        do {
            pr.print(">")
            var input: String = sc.nextLine()
            var quitting = false
            var currentName = ""
            var whitespace = input.isBlank()
            var userWhitespace = input.substringAfter(":user").isBlank()

            when {
                whitespace -> pr.println("You have to input something :v)")
                input.startsWith(":user") -> when  {
                    userWhitespace -> pr.println(Users.toString())
                    else -> {
                        Users.addUser(input.substringAfter(":user"))
                    }
                }
                input.startsWith(":") -> when (input.substringAfter(":")) {
                    "quit" -> quitting = true
                    "help" -> pr.println("Print HELP")
                    "history" -> pr.println(ChatHistory.toString())
                }
                else ->  if(Users.userList.isEmpty()) {
                    pr.println("Set username before posting :v)")
                } else {
                    pr.println(ChatMessage(input, currentName).toString())
                    ChatHistory.insert(ChatMessage(input, currentName))
                }

            }
        } while (!quitting)
    }
}