import java.io.InputStream
import java.io.PrintStream
import java.util.Scanner

class CommandInterpreter(inputStream: InputStream, printStream: PrintStream) {
    val sc = Scanner(System.`in`)

    fun run() {
        do {
            print(">")
            var input: String = sc.nextLine()
            var quitting = false
            var whitespace = input.isBlank()
            var userWhitespace = input.substringAfter(":user").isBlank()
            when {
                whitespace -> println("You have to input something :v)")
                //input == "" -> println("You have to input something :v)")
                // (input.substringAfter(":user"))
                input.startsWith(":user") -> when  {
                    userWhitespace -> Users.listUsers()
                    else -> Users.addUser(input.substringAfter(":user"))
                }
                input.startsWith(":") -> when (input.substringAfter(":")) {
                    "quit" -> quitting = true
                    "help" -> println("Print HELP")
                }
                else ->  if(Users.userList.isEmpty()) {
                            println("Set username before posting :v)")
                         } else {
                               println(ChatMessage(input, "asdf").toString())

                         }
            }
        } while (!quitting)
    }
}