package com.devfuse.mk.repository

import com.devfuse.mk.entity.Message
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface MessageRepository : JpaRepository<Message, Int> {

    fun findByMessageId(messageId: Int): Message
    fun existsByUidAndExpose(uid: String, expose: Boolean): Boolean
    fun countAllByExpose(expose: Boolean): Int
    fun findByUidAndExpose(uid: String, expose: Boolean): Message?
    fun findAllByExpose(expose: Boolean, pageable: Pageable): Page<Message>

}