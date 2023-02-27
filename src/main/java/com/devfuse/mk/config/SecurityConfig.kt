package com.devfuse.mk.config

import com.devfuse.mk.filter.JwtFilter
import com.devfuse.mk.service.UserService
import com.google.firebase.auth.FirebaseAuth
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter(), WebMvcConfigurer {
    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var firebaseAuth: FirebaseAuth

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().configurationSource(corsConfigurationSource()).and()
            .csrf().disable()
            .authorizeRequests()
            .anyRequest().authenticated().and()
            .addFilterBefore(
                JwtFilter(userService, firebaseAuth),
                UsernamePasswordAuthenticationFilter::class.java
            )
            .exceptionHandling()
            .authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.addAllowedOrigin("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.allowCredentials = true
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        //spring security 제외 경로설정
        web.ignoring()
            .antMatchers(HttpMethod.GET, "/mk/token/**")
            .antMatchers(HttpMethod.GET, "/mk/message")
            .antMatchers(HttpMethod.POST, "/mk/user")
            .antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**")
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT")
    }
}