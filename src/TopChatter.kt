
object TopChatter: ChatObserver {
    private val leaderboard = mutableMapOf<String, Int>()
    init {
        ChatHistory.registerObserver(this)
    }

    override fun toString(): String {
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