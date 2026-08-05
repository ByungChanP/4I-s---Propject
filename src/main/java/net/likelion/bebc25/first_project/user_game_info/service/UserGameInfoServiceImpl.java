package net.likelion.bebc25.first_project.user_game_info.service;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.repository.UserGameInfoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserGameInfoServiceImpl implements UserGameInfoService {

    private final UserGameInfoRepository userGameInfoRepository;

    public UserGameInfoServiceImpl(@Qualifier("jdbcTemplateUserGameInfoRepository") UserGameInfoRepository userGameInfoRepository) {
        this.userGameInfoRepository = userGameInfoRepository;
    }

    @Override
    public void register(int memberId, int gameId, IngameInfoDto ingameInfoDto) {

        switch (gameId) {
            case 1 -> {
                InfoDto infoDto = InfoDto.builder()
                        .game_id(gameId)
                        .member_id(memberId)
                        .lolIngameInfo(ingameInfoDto)
                        .build();
                userGameInfoRepository.save(infoDto);
            }
            case 2 -> {
                InfoDto infoDto = InfoDto.builder()
                        .game_id(gameId)
                        .member_id(memberId)
                        .lostarkIngameInfo(ingameInfoDto)
                        .build();

                userGameInfoRepository.save(infoDto);
            }
            default -> throw new IllegalArgumentException("지원하지 않는 인게임 프로필 타입입니다.");
        }
    }

    @Override
    public void modifyInfo(InfoDto gameInfo) {

    }

    @Override
    public InfoDto getInfo(int game_iD, int member_id) {
        return userGameInfoRepository.findByID(game_iD, member_id);
    }
}
