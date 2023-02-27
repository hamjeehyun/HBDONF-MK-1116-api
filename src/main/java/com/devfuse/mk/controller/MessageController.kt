package com.devfuse.mk.controller

import com.devfuse.mk.domain.common.CommonResponseDto
import com.devfuse.mk.domain.dto.message.MessageListResDto
import com.devfuse.mk.domain.dto.message.MessageReqDto
import com.devfuse.mk.domain.dto.message.MessageResDto
import com.devfuse.mk.service.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("mk/message")
class MessageController : CommonController() {
    @Autowired
    lateinit var messageService: MessageService

    @PostMapping("")
    fun registerMessage(
        @RequestBody messageReqDto: MessageReqDto
    ): ResponseEntity<CommonResponseDto<String>> {
        return ResponseEntity.ok(
            getResponse(
                messageService.registerMessage(messageReqDto)
            )
        )
    }

    @GetMapping("")
    fun getMessageList(
        @RequestParam(required = false, defaultValue = "0") size: Int
    ): ResponseEntity<CommonResponseDto<MessageListResDto>> {
        return ResponseEntity.ok(
            getResponse(
                messageService.getMessageList(size)
            )
        )
    }

    @GetMapping("/user/{uid}")
    fun getUserMessage(
        @PathVariable(name = "uid") uid: String
    ): ResponseEntity<CommonResponseDto<MessageResDto>> {
        return ResponseEntity.ok(
            getResponse(messageService.getUserMessage(uid))
        )
    }

    @PutMapping("/{message-id}")
     fun deleteMessage(
        @PathVariable(name = "message-id") messageId: Int
    ): ResponseEntity<CommonResponseDto<String>> {
        return ResponseEntity.ok(
            getResponse(messageService.deleteMessage(messageId))
        )
    }
}