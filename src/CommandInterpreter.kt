import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.Scanner
class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream) {
   // val sc = Scanner(System.`in`)
    val sc = Scanner(inputStream)
    val pr = PrintStream(outputStream)
    fun run() {
        do {
            pr.print(">")
            var input: String = sc.nextLine()
            var quitting = false
            var whitespace = input.isBlank()
            var userWhitespace = input.substringAfter(":user").isBlank()
            when {
                whitespace -> pr.println("You have to input something :v)")
                //input == "" -> pr.println("You have to input something :v)")
                // (input.substringAfter(":user"))
                input.startsWith(":user") -> when  {
                    userWhitespace -> pr.println(Users.toString())
                    else -> Users.addUser(input.substringAfter(":user"))
                }
                input.startsWith(":") -> when (input.substringAfter(":")) {
                    "quit" -> quitting = true
                    "help" -> pr.println("Print HELP")
                    //TODO History prints to console
                    "history" -> pr.println(ChatHistory.toString())
                }
                else ->  if(Users.userList.isEmpty()) {
                    pr.println("Set username before posting :v)")
                } else {
                    pr.println(ChatMessage(input, "asdf").toString())
                    ChatHistory.insert(ChatMessage(input, "asdf"))
                }

            }
        } while (!quitting)
    }
}