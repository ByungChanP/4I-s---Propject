package net.likelion.bebc25.first_project.game.service;

import net.likelion.bebc25.first_project.game.dto.GameDto;

import java.util.List;

public interface GameService {
    List<GameDto> getGames();

    GameDto getGame(int id);
}
