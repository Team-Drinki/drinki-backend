package io.github.teamdrinki.drinkibackend.domain.tastingnote.repository

import io.github.teamdrinki.drinkibackend.common.dto.PagedListResult
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteDetailResponse
import io.github.teamdrinki.drinkibackend.domain.tastingnote.data.response.TastingNoteListItem
import io.github.teamdrinki.drinkibackend.schema.AlcoholCategories
import io.github.teamdrinki.drinkibackend.schema.Alcohols
import io.github.teamdrinki.drinkibackend.schema.CommentTargetType
import io.github.teamdrinki.drinkibackend.schema.Comments
import io.github.teamdrinki.drinkibackend.schema.TastingNotes
import io.github.teamdrinki.drinkibackend.schema.Users
import io.github.teamdrinki.drinkibackend.schema.entity.TastingNoteEntity
import org.jetbrains.exposed.v1.core.Op
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.eq
import org.jetbrains.exposed.v1.core.SqlExpressionBuilder.like
import org.jetbrains.exposed.v1.core.alias
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.core.innerJoin
import org.jetbrains.exposed.v1.core.leftJoin
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Repository
import kotlin.collections.map

@Repository
class TastingNoteRepositoryImpl : TastingNoteRepository {
    override fun findAllByFilters(
        query: String?,
        category: String?,
        page: Int,
        size: Int,
        sort: String
    ): PagedListResult<TastingNoteListItem> {
        val offset = (page - 1) * size
        var condition: Op<Boolean> = Op.TRUE

        // 테이블 조인
        val joinedTastingNotes = TastingNotes
            .innerJoin(Alcohols)
            .innerJoin(AlcoholCategories)
            .innerJoin(Users) {
                TastingNotes.userId eq Users.id
            }.leftJoin(Comments, { TastingNotes.id }, { Comments.targetId }) {
                Comments.targetType eq CommentTargetType.tasting_note
            }

        if (!query.isNullOrBlank()) {    // 검색어
            condition = condition and (TastingNotes.title like "%$query%")
        }

        if (!category.isNullOrBlank()) {    // 카테고리
            condition = condition and (AlcoholCategories.name eq category)
        }

        return transaction {
            val totalCnt = joinedTastingNotes
                .select(condition).count()

            val entities = joinedTastingNotes
                .select(TastingNotes.columns + Users.columns + Alcohols.columns + AlcoholCategories.columns + Comments.id.count().alias("comment_count"))
                .where(condition)
                .groupBy(TastingNotes.id, Users.id, Alcohols.id, AlcoholCategories.id)
                .limit(size).offset(offset.toLong())
                .orderBy(TastingNotes.createdAt, SortOrder.DESC) //  TODO: sort 기준 수정
                .map { row ->
                    val tastingNote = TastingNoteEntity.wrapRow(row)

                    TastingNoteListItem(
                        noteId = tastingNote.id.value,
                        noteTitle = tastingNote.title,
                        alcoholCategory = row[AlcoholCategories.name],
                        alcoholName = row[Alcohols.name],
                        noteImage = tastingNote.image_url?:"",
                        writer = row[Users.nickname],
                        commentNum = 1,
                        like = 1,
                        unlike = 1,
                        viewer = 1,
                        createdTime = tastingNote.createdAt.toString(),
                    )
                }
            PagedListResult(entities, totalCnt)
        }
    }

    override fun findDetailById(noteId: Long): TastingNoteDetailResponse? {
        return transaction {
            val joinedTastingNotes = TastingNotes
                .innerJoin(Users) {
                    TastingNotes.userId eq Users.id
                }

            val entities = joinedTastingNotes
                .select(TastingNotes.columns + Users.columns)
                .where { TastingNotes.id eq noteId }
                .map { row ->
                    val tastingNote = TastingNoteEntity.wrapRow(row)

                    TastingNoteDetailResponse(
                        noteId = tastingNote.id.value,
                        title = tastingNote.title,
                        writerId = row[Users.id].value,
                        writerName = row[Users.nickname],
                        writerImage = row[Users.profileImageUrl] ?: "",
                        like = 1,   // TODO: 좋아요, 싫어요, 조회수 로직 추가
                        unlike = 1,
                        viewer = 1,
                        createdTime = tastingNote.createdAt.toString(), // TODO: 시간 포맷팅
                        aroma_note = tastingNote.aromaNote,
                        palate_note = tastingNote.palateNote,
                        finish_note = tastingNote.finishNote,
                        images = listOf(TastingNotes.image_url.toString()),
                        comments = listOf()
                    )
                }.firstOrNull()
            entities
        }
    }

    override fun findById(noteId: Long): TastingNoteEntity? {
        return transaction {
            TastingNoteEntity.findById(noteId)
        }
    }

    override fun delete(note: TastingNoteEntity) {
        transaction {
            note.delete()
        }
    }
}