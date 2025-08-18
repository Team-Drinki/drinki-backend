package io.github.ssudrinki.drinkibackend.service

import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import schema.UserEntity
import schema.UserRole
import schema.Users

@Service
class CustomOAuth2UserService() : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val delegate = DefaultOAuth2UserService()

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
                    this.socialId   = socialId
                    this.nickname = "unknown"
                    this.role = UserRole.USER.name
                    this.isDeleted = false
            }
        }

        return oauthUser
    }
}
