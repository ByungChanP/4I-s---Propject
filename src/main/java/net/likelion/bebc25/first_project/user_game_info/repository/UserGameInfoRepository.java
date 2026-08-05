package net.likelion.bebc25.first_project.user_game_info.repository;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;


public interface UserGameInfoRepository {

    /**
     * 새로운 인게임 정보를 저장합니다.
     *
     * @param gameInfo 저장할 인게임 정보  DTO
     */
    void save(InfoDto gameInfo);
    
    /**
     * 기존 인게임 정보를 수정합니다
     *
     * @param gameInfo 수정할 인게임 정보 객체
     */
    void update(InfoDto gameInfo);

    /**
     * 프로필에 등록한 인게임 정보를 조회
     *
     * @param gameId   해당 게임 ID
     * @param memberId 해당 유저 ID
     * @return
     */
    InfoDto findByID(int gameId, int memberId);

}
