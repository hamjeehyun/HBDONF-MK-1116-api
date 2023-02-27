package com.devfuse.mk.controller

import com.devfuse.mk.domain.common.CommonResponseDto
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

@RequiredArgsConstructor
@Controller
abstract class CommonController {

    fun <T> getResponse(payload: T, status: String = "200"): CommonResponseDto<T> {
        return CommonResponseDto(
            status = status,
            payload = payload,
            timestamp = LocalDateTime.now(),
        )
    }
}