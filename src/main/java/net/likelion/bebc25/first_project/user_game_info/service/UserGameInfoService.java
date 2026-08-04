package net.likelion.bebc25.first_project.user_game_info.service;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;

import java.util.List;

public interface UserGameInfoService {

    /**
     * 신규 등록을 처리합니다.
     *
     * @param gameInfo 인게임 정보를 요청할 DTO
     */
    void register(int userId, int gameId, IngameInfoDto ingameInfoDto);

    /**
     * 인게임 정보를 수정합니다.
     *
     * @param gameInfo
     */
    void modifyInfo(InfoDto gameInfo);

    /**
     * 해당 게임 정보를 조회합니다.
     *
     * @return
     */
    List<InfoDto> getInfo(int game_iD, int member_id);


}
