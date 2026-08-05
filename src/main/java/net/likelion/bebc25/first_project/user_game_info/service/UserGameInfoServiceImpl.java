package net.likelion.bebc25.first_project.user_game_info.service;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkIngameInfoDto;
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
    public void register(int memberId, int gameId, Object ingameInfoDto) {

        if (ingameInfoDto instanceof IngameInfoDto dto) {

            InfoDto infoDto = InfoDto.builder()
                    .game_id(gameId)
                    .member_id(memberId)
                    .ingame_info(dto)
                    .build();

            userGameInfoRepository.save(infoDto);

        } else if (ingameInfoDto instanceof LostarkIngameInfoDto dto) {

            LostarkInfoDto infoDto = LostarkInfoDto.builder()
                    .game_id(gameId)
                    .member_id(memberId)
                    .lostarkInfoDto(dto)
                    .build();

            userGameInfoRepository.save(infoDto);

        } else {
            throw new IllegalArgumentException(
                    "지원하지 않는 인게임 프로필 타입입니다."
            );
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
