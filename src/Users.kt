object Users {
    val userList = HashSet<String>()
    fun addUser(name: String) {
        Users.userList.add(name)
    }

    fun removeUser(name: String) {
        Users.userList.remove(name)
    }

    override fun toString(): String {
        var toprint = ""
        for (name in userList) {
            toprint += name + "\n"
        }
        println(toprint)
        return toprint
    }
}