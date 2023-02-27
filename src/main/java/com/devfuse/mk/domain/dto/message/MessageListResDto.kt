package com.devfuse.mk.domain.dto.message

data class MessageListResDto(
    var count: Int?,
    var messages: MutableList<MessageResDto>
)
