package io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response

data class TastingNoteDetailResponse (
    val noteId: Long,
    val title: String,
    val writerId: Long,
    val writerName: String,
    val writerImage: String,
    val like: Int,
    val unlike: Int,
    val viewer: Int,
    val createdTime: String,

    val aroma_note: Map<String, Map<String, Double>>,
    val palate_note: Map<String, Map<String, Double>>,
    val finish_note: Map<String, Map<String, Double>>,

    val images: List<String>,

    val comments: List<CommentResponseItem>
)