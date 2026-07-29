package net.likelion.bebc25.first_project.game.repository;

import net.likelion.bebc25.first_project.game.dto.GameDto;

import java.util.List;

public interface GameRepository {
    GameDto findById(int id);

    List<GameDto> findAll();
}
