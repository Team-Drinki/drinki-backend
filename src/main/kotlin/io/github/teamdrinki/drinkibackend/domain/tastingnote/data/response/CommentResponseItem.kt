package io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response

data class CommentResponseItem(
    val commentId: Int,
    val parentId: Int?,
    val writerNickName: String,
    val writerUserName: String,
    val content: String,
    val like: Int,
    val unlike: Int,
    val createdTime: String,
)
