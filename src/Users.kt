import java.lang.StringBuilder

object Users {
    val sb = StringBuilder()
    val userList = HashSet<String>()
    // REPLACE with SET
    fun addUser(name: String) {
        if (userList.contains(name)) println("Please input a unique username.")
        else userList.add(name)
        println(userList.hashCode())
    }

    override fun toString(): String {
        var toprint = ""
        for (name in userList) {
            toprint = sb.append(name).append("\n").toString()
        }
        return toprint
    }
    /*fun listUsers():  {
        for (name in userList) {
            println(name)
        }
    }*/
}