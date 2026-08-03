package net.likelion.bebc25.first_project.game.service;

import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameDto> getGames() {
        return gameRepository.findAll();
    }

    @Override
    public GameDto getGame(int id) {
        return gameRepository.findById(id);
    }
}
