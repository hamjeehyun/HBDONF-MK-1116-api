package com.devfuse.mk.domain.common

import java.time.LocalDateTime

data class CommonResponseDto<T>(
    var timestamp: LocalDateTime?,
    var status: String? = "200",
    var payload: T? = null
)
