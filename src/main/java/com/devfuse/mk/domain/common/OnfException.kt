package com.devfuse.mk.domain.common

class OnfException : RuntimeException {
    var code: String? = null
    override var message: String
    override var cause: Throwable? = null

    constructor(message: String) {
        this.message = message
    }

    constructor(code: String?, message: String) {
        this.message = message
        this.code = code
    }

    constructor(message: String, cause: Throwable?) {
        this.message = message
        this.cause = cause
    }
}