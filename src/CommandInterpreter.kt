import java.io.InputStream
import java.io.OutputStream
import java.io.PrintStream
import java.time.LocalDateTime
import java.util.*
class CommandInterpreter(inputStream: InputStream, outputStream: OutputStream): Runnable, ChatObserver {

    val sc = Scanner(inputStream)
    val pr = PrintStream(outputStream)

    override fun run() {
        var currentName = ""
        ChatHistory.registerObserver(this)
        TopChatter.toString()
        do {
            var input: String = sc.nextLine()
            var quitting = false
            var whitespace = input.isBlank()
            var userWhitespace = input.substringAfter(":user").isBlank()

            when {
                whitespace -> pr.println("You have to input something :v)")
                input.startsWith(":user") -> when  {
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
                }
                input.startsWith(":removeuser") -> {
                    var toRemove = input.substringAfter(":removeuser").replace("\\s".toRegex(), "")
                    when (toRemove) {
                        "" -> pr.println("Please give an username to remove! :v)")
                        else -> {
                            if (Users.userList.contains(toRemove)) {
                                if (toRemove == currentName) currentName = ""
                                Users.removeUser(toRemove)
                                pr.println("User $(toRemove) removed!")
                            } else {
                                pr.println("No such user exists! :v)")
                            }
                        }
                    }
                }
                input.startsWith(":") -> when (input.substringAfter(":")) {
                    "quit" -> {
                        quitting = true
                        ChatHistory.deregisterObserver(this)
                    }
                    "help" -> pr.println("Available commands: \n * :user - Add new user or list all users\n * :history - List last 10 messages\n * :online - Lists user's number of messages\n * :help - Display this message\n * :quit - Exit the chat")
                    "history" -> pr.println(ChatHistory.toString())
                    "online" -> pr.println(TopChatter.toString())
                }
                else ->  if(currentName == "") {
                    pr.println("Set username before posting :v)")
                } else {
                    ChatHistory.insert(ChatMessage(input, currentName, LocalDateTime.now()))
                    ChatHistory.notifyObserver(ChatMessage(input, currentName, LocalDateTime.now()))
                }

            }
        } while (!quitting)
    }

    override fun newMessage(message: ChatMessage) {
        pr.println(message)
    }
}