package io.github.teamdrinki.drinkibackend.domain.auth.service

import io.github.teamdrinki.drinkibackend.schema.UserEntity
import io.github.teamdrinki.drinkibackend.schema.UserRole
import io.github.teamdrinki.drinkibackend.schema.Users
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

/**
 * OAuth2 사용자 정보를 처리하는 서비스 클래스입니다.
 */
@Service
class CustomOAuth2UserService(
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val delegate = DefaultOAuth2UserService()

    /**
     * OAuth2 사용자 정보를 가져옵니다.
     * 사용자가 존재하지 않으면 새로 생성합니다.
     *
     * @param request OAuth2 사용자 요청 정보
     * @return OAuth2 사용자 정보
     */
    override fun loadUser(request: OAuth2UserRequest): OAuth2User {
        val oauthUser = delegate.loadUser(request)
        val attrs     = oauthUser.attributes

        val socialType = request.clientRegistration.registrationId

        val socialId = attrs["sub"]   as String

        transaction {
            val user = UserEntity
                .find { Users.socialId eq socialId }
                .singleOrNull()
                ?: UserEntity.new {
                    this.socialType = socialType
                    this.socialId = socialId
                    this.nickname = "unknown"
                    this.role = UserRole.USER.name
                    this.isDeleted = false
                }
        }

        return oauthUser
    }
}