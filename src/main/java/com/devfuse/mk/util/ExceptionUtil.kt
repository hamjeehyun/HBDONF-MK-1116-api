package com.devfuse.mk.util

import com.devfuse.mk.config.ExceptionMessageConfig
import com.devfuse.mk.domain.common.OnfException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.text.MessageFormat
import javax.annotation.PostConstruct

@Component
class ExceptionUtil {
    @Autowired
    private val loader: ExceptionMessageConfig? = null

    @PostConstruct
    fun registerInstance() {
        sLoader = loader
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ExceptionUtil::class.java)
        private var sLoader: ExceptionMessageConfig? = null

        fun createOnfBizException(errorCode: String?): OnfException? {
            return generateMessage(errorCode)?.let { OnfException(errorCode, it) }
        }

        fun <T> createOnfBizException(errorCode: String?, param: T): OnfException {
            return createOnfBizException(errorCode, arrayOf<String>(param.toString()))
        }

        fun createOnfBizException(errorCode: String?, params: Array<Any?>?): OnfException? {
            return generateMessage(errorCode, params)?.let { OnfException(errorCode, it) }
        }

        @JvmOverloads
        fun generateMessage(errorCode: String?, params: Array<Any?>? = null): String? {
            var errorMessage = getErrorMessage(errorCode)
            return if (errorMessage == null) {
                null
            } else {
                if (params != null) {
                    errorMessage = applyFormat(errorMessage, params)
                }
                errorMessage
            }
        }

        private fun applyFormat(errorMessage: String, params: Array<Any?>): String {
            val mf = MessageFormat(errorMessage)
            return mf.format(params)
        }

        private fun getErrorMessage(errorCode: String?): String? {
            return errorCode?.let { sLoader?.loadMessages()?.get(it) }
        }
    }
}