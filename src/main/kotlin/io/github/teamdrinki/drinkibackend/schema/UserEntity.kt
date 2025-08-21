package schema

import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(Users)

    var socialType      by Users.socialType
    var socialId        by Users.socialId
    var nickname        by Users.nickname
    var profileImageUrl by Users.profileImageUrl
    var role            by Users.role
    var isDeleted       by Users.isDeleted
    var createdAt       by Users.createdAt
    var updatedAt       by Users.updatedAt
}
