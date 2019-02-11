class ChatConsole(): ChatObserver{
    init {
        ChatHistory.registerObserver(this)
    }
    override fun newMessage(message: ChatMessage) {
            println(message.toString())
    }
}