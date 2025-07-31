package io.github.ssudrinki.drinkibackend.service

import io.github.ssudrinki.drinkibackend.db.OAuthUserEntity
import io.github.ssudrinki.drinkibackend.db.OAuthUsers
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val clientService: OAuth2AuthorizedClientService
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private val delegate = DefaultOAuth2UserService()

    override fun loadUser(request: OAuth2UserRequest): OAuth2User {
        val oauthUser = delegate.loadUser(request)
        val attrs     = oauthUser.attributes

        val provider = request.clientRegistration.registrationId

        val socialId = attrs["sub"]   as String
        val email    = attrs["email"] as String?

        transaction {
            val user = when {
                email != null ->
                    OAuthUserEntity
                        .find { OAuthUsers.email eq email }
                        .singleOrNull()
                else ->
                    OAuthUserEntity
                        .find { OAuthUsers.socialId eq socialId }
                        .singleOrNull()
            } ?: OAuthUserEntity.new {
                this.email      = email
                this.socialType = provider
                this.socialId   = socialId
            }
        }

        return oauthUser
    }
}
