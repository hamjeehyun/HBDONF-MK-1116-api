package com.devfuse.mk.domain.dto.user

import com.devfuse.mk.entity.User
import java.time.LocalDateTime

data class UserResDto(
    var uid: String?,
    var tid: String?,
    var region: String?,
    var tnickName: String?,
    var created: LocalDateTime?,
    var updated: LocalDateTime?
) {
    constructor(
        user: User
    ) : this(
        uid = user.uid,
        tid = user.tid,
        region = user.region,
        tnickName = user.tnickName,
        created = user.created,
        updated = user.updated,
    )
}
