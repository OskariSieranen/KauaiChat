
object TopChatter: ChatObserver {
    private val leaderboard = mutableMapOf<String, Int>()
    init {
        ChatHistory.registerObserver(this)
    }

    /*fun sortedCheck(): String {
        var toprint = ""
        var sorted = leaderboard.toSortedMap()
        for ((key, value) in sorted) {
            toprint += "Users: $key Posts: $value \n"
        }
        return toprint

    }*/
    override fun toString(): String {
        //return leaderboard.forEach {key, value -> println("User: $key Posts: $value") }
        var toprint = ""
        for ((key, value) in leaderboard) {
            toprint += "User: $key Posts: $value \n"
        }
        return toprint
    }
    override fun newMessage(message: ChatMessage) {
        var userName = message.sender
        if (!leaderboard.containsKey(userName)) {
            leaderboard[userName] = 1
        } else {
            var posts = leaderboard[userName]
            if (posts != null) {
                posts += 1
                leaderboard.set(userName, posts)
            }
        }
    }

}