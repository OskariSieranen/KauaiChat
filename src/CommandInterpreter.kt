import java.io.BufferedOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.util.*
import kotlin.test.currentStackTrace
class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream): Runnable, ChatObserver {

    val sc = Scanner(inputStream)
    val pr = PrintStream(outputStream)
    override fun run() {
        var currentName = ""
        ChatHistory.registerObserver(this)
        do {
            pr.print(">")
            var input: String = sc.nextLine()
            var quitting = false
            var whitespace = input.isBlank()
            var userWhitespace = input.substringAfter(":user").isBlank()

            when {
                whitespace -> pr.println("You have to input something :v)")
                input.startsWith(":user") -> when  {
                    //userWhitespace -> pr.println(Users.toString())
                    !userWhitespace -> {
                        var fixedInput = input.substringAfter(":user").replace("\\s".toRegex(), "")
                        if (Users.userList.contains(fixedInput)) {
                            pr.println("Please input a unique username. :v)")
                        } else {
                            currentName = fixedInput
                            Users.addUser(fixedInput)
                            println(fixedInput)
                        }
                    }
                    else -> pr.println(Users.toString())
                    /*else -> {
                        Users.addUser(input.substringAfter(":user"))
                        currentName = input.substringAfter(":user")
                        println(input.substringAfter(":user"))
                    }*/
                }
                input.startsWith(":") -> when (input.substringAfter(":")) {
                    "quit" -> {
                        quitting = true
                        ChatHistory.deregisterObserver(this)
                    }
                    "help" -> pr.println("Available commands: \n * :user - Add new user or list all users\n * :history - List last 10 messages\n * :help Display this message\n * :quit - Exit the chat")
                    "history" -> pr.println(ChatHistory.toString())
                }
                else ->  if(currentName == "") {
                    pr.println("Set username before posting :v)")
                } else {
                    ChatHistory.insert(ChatMessage(input, currentName))
                    ChatHistory.notifyObserver(ChatMessage(input, currentName))
                }

            }
        } while (!quitting)
    }

    override fun newMessage(message: ChatMessage) {
        pr.println(message)
    }
}