package net.likelion.bebc25.first_project.user_game_info.service;

import net.likelion.bebc25.first_project.user_game_info.dto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.repository.UserGameInfoRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGameInfoServiceImpl implements UserGameInfoService {

    private final UserGameInfoRepository userGameInfoRepository;

    public UserGameInfoServiceImpl(@Qualifier("jdbcTemplateUserGameInfoRepository")UserGameInfoRepository userGameInfoRepository) {
        this.userGameInfoRepository =userGameInfoRepository;
    }

    @Override
    public void register(InfoDto gameInfo) {

    }

    @Override
    public void modifyInfo(InfoDto gameInfo) {

    }

    @Override
    public List<InfoDto> getInfo(int game_iD, int member_id) {
        return List.of();
    }
}
