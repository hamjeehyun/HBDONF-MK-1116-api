package com.devfuse.mk.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "message")
data class Message(
    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val messageId: Int? = null,
    @Column(name = "uid")
    val uid: String? = null,
    @Column(name = "level")
    val level: Int? = null,
    @Column(name = "content")
    val content: String? = null,
    @Column(name = "expose")
    val expose: Boolean = false,
    @Column(name = "created")
    val created: LocalDateTime? = null,
    @Column(name = "updated")
    val updated: LocalDateTime? = null
)
