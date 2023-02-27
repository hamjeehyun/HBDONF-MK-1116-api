package com.devfuse.mk.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*


@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .paths(PathSelectors.ant("/mk/**"))
            .build()
            .apiInfo(metaData())
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Arrays.asList(apiKey()))
    }

    private fun metaData(): ApiInfo? {
        return ApiInfoBuilder()
            .title("민균아 사랑해")
            .description("HBDONF-MK-1116-api")
            .version("1.0.0")
            .build()
    }

    private fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    private fun securityContext(): SecurityContext {
        return SecurityContext
            .builder()
            .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build()
    }

    fun defaultAuth(): List<SecurityReference>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return Arrays.asList(SecurityReference("JWT", authorizationScopes))
    }
}