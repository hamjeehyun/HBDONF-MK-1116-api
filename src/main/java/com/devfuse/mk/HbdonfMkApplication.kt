package com.devfuse.mk

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@EntityScan
@EnableJpaRepositories
@SpringBootApplication
class HbdonfMkApplication

fun main(args: Array<String>) {
    runApplication<HbdonfMkApplication>(*args)
}
