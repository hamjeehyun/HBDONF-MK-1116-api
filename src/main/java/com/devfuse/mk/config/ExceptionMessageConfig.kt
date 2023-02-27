package com.devfuse.mk.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Configuration
@EnableConfigurationProperties(ExceptionMessageProperty::class)
@Component
@Scope("singleton")
class ExceptionMessageConfig {
    @Autowired
    private val exMessageProperty: ExceptionMessageProperty? = null

    /**
     * 메시지 저장소에서 메시지를 로드한다.
     */
    fun loadMessages(): Map<String, String> {
        return exMessageProperty?.bizMessages ?: HashMap()
    }
}