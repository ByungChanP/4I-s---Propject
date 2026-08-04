package net.likelion.bebc25.first_project.user_game_info.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.repository.UserGameInfoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserGameInfoServiceImpl implements UserGameInfoService {

    private final UserGameInfoRepository userGameInfoRepository;

    public UserGameInfoServiceImpl(@Qualifier("jdbcTemplateUserGameInfoRepository") UserGameInfoRepository userGameInfoRepository) {
        this.userGameInfoRepository = userGameInfoRepository;
    }

    @Override
    public void register(int memberId, int gameId, IngameInfoDto ingameInfoDto) {

        InfoDto infoDto = InfoDto.builder()
                .game_id(gameId)
                .member_id(memberId)
                .ingame_info(ingameInfoDto).build();

        userGameInfoRepository.save(infoDto);
    }

    @Override
    public void modifyInfo(InfoDto gameInfo) {

    }

    @Override
    public List<InfoDto> getInfo(int game_iD, int member_id) {
        return List.of();
    }
}
