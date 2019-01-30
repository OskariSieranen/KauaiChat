import java.io.InputStream
import java.io.PrintStream
import java.util.Scanner

class CommandInterpreter(inputStream: InputStream, printStream: PrintStream) {
    val sc = Scanner(System.`in`)

    fun run() {
        print(">")
        var input: String = sc.nextLine()
        if (input == "") {
            println("You have to input something :v)")
        } else if (input.startsWith(":") ) {
            // add commands here
            println("I am running commands")
        } else {
            println(input)
        }

    }
}