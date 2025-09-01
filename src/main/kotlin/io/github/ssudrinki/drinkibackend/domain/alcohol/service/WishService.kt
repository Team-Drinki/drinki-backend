package io.github.ssudrinki.drinkibackend.domain.alcohol.service

/**
 * 술의 위시리스트 관련 비즈니스 로직을 처리하는 서비스
 *
 * 이 서비스는 술의 위시리시트 추가, 삭제, 체크 등의 유저 위시리스트 로직을 담당합니다.
 */
interface WishService {

    /**
     * 사용자의 위시리스트에 술을 추가합니다.
     *
     * 현재 로그인한 사용자와 지정된 술 간의 위시 관계를 생성합니다.
     *
     * @param alcoholId 위시리스트에 추가할 술의 ID
     * @throws AlreadyWishedException 이미 위시리스트에 있는 경우
     * @throws AlcoholNotFoundException 존재하지 않는 술인 경우
     */
    fun addWish(alcoholId: Int)

    /**
     * 사용자의 위시리스트에서 술을 제거합니다.
     *
     * 현재 로그인한 사용자와 지정된 술 간의 위시 관계를 삭제합니다.
     *
     * @param alcoholId 위시리스트에서 제거할 술의 ID
     * @throws WishNotFoundException 위시리스트에 없는 경우
     * @throws AlcoholNotFoundException 존재하지 않는 술인 경우
     */
    fun removeWish(alcoholId: Int)

    /**
     * 사용자의 위시리스트에 현재 술이 존재하는지 체크합니다.
     *
     * 로그인 한 사용자라면 위시리스트를 체크하고, 로그인하지 않은 사용자라면 체크하지 않습니다
     *
     * @param alcoholId 위시리스트에서 체크할 술의 ID
     * @param userId    체크할 유저의 ID
     * @throws
     */
    fun isWished(alcoholId: Int, userId: Int?)

    fun getWishList(alcoholId: Int, userId: Int)

}