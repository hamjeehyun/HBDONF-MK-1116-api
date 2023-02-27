package com.devfuse.mk.exception

class ServiceException() : Exception() {
    var serviceMessage: ServiceMessage = ServiceMessage()
    var messageCode: String? = null
    override var message: String? = null


    constructor(messageCode: String?) : this() {
        this.messageCode = messageCode
        message = messageCode?.let { serviceMessage.getMessage(it) }
    }


    constructor(messageCode: String?, messageParams: Array<String>) : this() {
        this.messageCode = messageCode
        message = messageCode?.let { serviceMessage.getMessage(it, messageParams) }
    }
}