package com.devfuse.mk.service

import com.devfuse.mk.domain.dto.message.MessageListResDto
import com.devfuse.mk.domain.dto.message.MessageReqDto
import com.devfuse.mk.domain.dto.message.MessageResDto
import com.devfuse.mk.entity.Message
import com.devfuse.mk.repository.MessageRepository
import com.devfuse.mk.repository.UserRepository
import com.devfuse.mk.util.ExceptionUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MessageService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    @Autowired
    lateinit var userRepository: UserRepository

    fun registerMessage(
        message: MessageReqDto
    ): String {
        val userMessage = messageRepository.existsByUidAndExpose(message.uid!!, true)
        val userExists = userRepository.existsByUid(message.uid!!)

        if (message.content!!.length > 140)
        // 140자 이상은 등록할 수 없습니다.
            throw ExceptionUtil.createOnfBizException("ONF_0002")!!

        if (userMessage)
        // 이미 등록한 메세지가 있습니다.
            throw ExceptionUtil.createOnfBizException("ONF_0001")!!

        if (!userExists)
        // 등록 되지 않은 사용자 입니다.
            throw ExceptionUtil.createOnfBizException("ONF_0003")!!

        messageRepository.save(
            Message(
                uid = message.uid,
                content = message.content,
                level = message.level,
                expose = true, // 공개 상태로 등록

                created = LocalDateTime.now(),
                updated = LocalDateTime.now(),
            )
        )

        return "등록되었습니다."
    }

    fun getMessageList(
        size: Int,
    ): MessageListResDto {
        val pageRequest: PageRequest = PageRequest.of(0, size, Sort.by("created").descending())
        val messageList = messageRepository.findAllByExpose(true, pageRequest)
        val messageCount = messageRepository.countAllByExpose(true)

        val messageDtos: MutableList<MessageResDto> = mutableListOf()
        messageList.content.map { message ->
            val user = userRepository.findByUid(message.uid!!)
            messageDtos.add(MessageResDto(message, user))
        }.toList()

        return MessageListResDto(count = messageCount, messages = messageDtos)
    }

    fun getUserMessage(
        uid: String
    ): MessageResDto {
        val user = userRepository.findByUid(uid)
        val message = messageRepository.findByUidAndExpose(uid, true)

        if (user == null)
        // 등록 되지 않은 사용자 입니다.
            throw ExceptionUtil.createOnfBizException("ONF_0003")!!

        if (message == null) {
            return MessageResDto(message = null, user = null)
        } else {
            return MessageResDto(message = message, user = user)
        }
    }

    fun deleteMessage(
        messageId: Int
    ): String {
        val message = messageRepository.findByMessageId(messageId)

        messageRepository.save(
            message.copy(
                expose = false,
                updated = LocalDateTime.now(),
            )
        )

        return "삭제되었습니다."
    }
}