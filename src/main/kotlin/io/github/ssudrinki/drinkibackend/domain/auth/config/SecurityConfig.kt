package io.github.ssudrinki.drinkibackend.domain.auth.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import kotlin.jvm.java


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val jwt: JwtProvider,
    private val successHandler: GoogleAuthSuccessHandler,
    @Value("\${client.url}") private val clientUrl: String
){

    @Bean fun jwtAuthFilter() = JwtAuthFilter(jwt)

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() }

            cors { configurationSource = corsConfigurationSource() }

            authorizeHttpRequests {
                authorize("/", permitAll)
                authorize("/api/v1/auth/**", permitAll)
                authorize(anyRequest, authenticated)

                // 백엔드 API 테스트용
                authorize("/login.html", permitAll)
                authorize("/oauth2/success.html", permitAll)

            }
            oauth2Login {
                    authenticationSuccessHandler = successHandler
            }
            addFilterBefore(
                jwtAuthFilter(),
                UsernamePasswordAuthenticationFilter::class.java)
            sessionManagement {
                sessionCreationPolicy = SessionCreationPolicy.STATELESS
            }
        }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource =
        UrlBasedCorsConfigurationSource().apply {
            val corsConfiguration = CorsConfiguration().apply {
                allowedOrigins = listOf(clientUrl)
                allowedMethods = listOf("GET", "POST", "PUT", "DELETE")
                allowedHeaders = listOf("*")
                allowCredentials = true
            }
            registerCorsConfiguration("/**", corsConfiguration)
        }
}