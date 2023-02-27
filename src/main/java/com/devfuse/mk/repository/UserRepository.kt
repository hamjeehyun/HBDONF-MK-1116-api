package com.devfuse.mk.repository

import com.devfuse.mk.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, String> {
    fun findByUid(uid: String): User?
    fun existsByUid(uid: String): Boolean
}