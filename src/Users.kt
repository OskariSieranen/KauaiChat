import java.lang.StringBuilder
// TODO  removing username & Checking if the username exists already
object Users {
    val sb = StringBuilder()
    val userList = HashSet<String>()
    fun addUser(name: String) {
        if (userList.contains(name)) println("Please input a unique username.")
        else userList.add(name)
    }

    override fun toString(): String {
        var toprint = ""
        for (name in userList) {
            toprint = name + "\n"
        }
        return toprint
    }
}