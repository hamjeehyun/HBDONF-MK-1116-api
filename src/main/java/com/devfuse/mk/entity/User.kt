package com.devfuse.mk.entity

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
    @Id
    @Column(name = "uid")
    val uid: String? = null,
    @Column(name = "tid")
    val tid: String? = null,
    @Column(name = "region")
    val region: String? = null,
    @Column(name = "tnick_name")
    val tnickName: String? = null,
    @Column(name = "created")
    val created: LocalDateTime? = null,
    @Column(name = "updated")
    val updated: LocalDateTime? = null
)
