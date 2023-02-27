package com.devfuse.mk.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "onf")
class ExceptionMessageProperty {
    var bizMessages: Map<String, String>? = HashMap()
}