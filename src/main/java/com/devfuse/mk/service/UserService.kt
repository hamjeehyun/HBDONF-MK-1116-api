package com.devfuse.mk.service

import com.devfuse.mk.domain.dto.user.UserReqDto
import com.devfuse.mk.domain.dto.user.UserResDto
import com.devfuse.mk.entity.User
import com.devfuse.mk.repository.UserRepository
import com.devfuse.mk.util.ExceptionUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService {
    @Autowired
    lateinit var userRepository: UserRepository

    fun registerUser(
        user: UserReqDto
    ): String {
        userRepository.save(
            User(
                uid = user.uid,
                tid = user.tid,
                region = user.region,
                tnickName = user.tnickName,

                created = LocalDateTime.now(),
                updated = LocalDateTime.now(),
            )
        )
        return "등록되었습니다"
    }

    fun findUserByUid(
        uid: String
    ): UserResDto {
        val user = userRepository.findByUid(uid)
        if (user == null)
        // 등록 되지 않은 사용자 입니다.
            throw ExceptionUtil.createOnfBizException("ONF_0003")!!

        return UserResDto(user)
    }
}