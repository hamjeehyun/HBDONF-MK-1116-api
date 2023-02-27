package com.devfuse.mk.exception

class ServiceMessage {
    var serviceMessage: ServiceMessage? = null
    val message: MutableMap<String, String> = HashMap()

    constructor() {
        message["MESSAGE_001"] = "이미 등록한 메세지가 있습니다."
    }

    fun getMessage(messageCode: String): String? {
        if (serviceMessage == null) serviceMessage = ServiceMessage()
        return message[messageCode]
    }

    fun getMessage(messageCode: String, messageParams: Array<String>): String? {
        if (serviceMessage == null) serviceMessage = ServiceMessage()
        return message[messageCode]?.let { String.format(it, *messageParams) }
    }
}