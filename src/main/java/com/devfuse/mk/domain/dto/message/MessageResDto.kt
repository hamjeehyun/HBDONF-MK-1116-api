package com.devfuse.mk.domain.dto.message

import com.devfuse.mk.entity.Message
import com.devfuse.mk.entity.User
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@NoArgsConstructor
data class MessageResDto(
    var messageId: Int?,
    var uid: String?,
    var tid: String?,
    var tnickName: String?,
    var content: String?,
    var level: Int?,
    var created: LocalDateTime?,

    ) {
    constructor(
        message: Message?,
        user: User?
    ) : this(
        messageId = message?.messageId,
        uid = message?.uid,
        tid = user?.tid,
        tnickName = user?.tnickName,
        content = message?.content,
        level = message?.level,
        created = message?.created,
    )
}