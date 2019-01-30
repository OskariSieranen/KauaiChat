object Users {
    val userList = mutableSetOf<String>()
    // REPLACE with SET
    fun addUser(name: String) {
        if (userList.contains(name)) println("Please input a unique username.")
        else userList.add(name)
    }

    fun listUsers() {
        for (name in userList) {
            println(name)
        }
    }
}