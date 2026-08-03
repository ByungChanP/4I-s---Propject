package net.likelion.bebc25.first_project.game.repository;

import net.likelion.bebc25.first_project.game.dto.GameDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class JdbcTemplateGameRepository implements GameRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateGameRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<GameDto> gameRowMapper = (ResultSet rs, int rowNum) -> {
        return GameDto.builder()
                .id(rs.getInt("id"))
                .gameTitle(rs.getString("game_title"))
                .details(rs.getString("detail"))
                .logoImgDir(rs.getString("game_logo_dir"))
                .backgroundImgDir(rs.getString("background_img_dir"))
                .build();
    };

    @Override
    public GameDto findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM game WHERE id = ?", gameRowMapper, id);
    }

    @Override
    public List<GameDto> findAll() {
        return jdbcTemplate.query("SELECT * FROM game ORDER BY game_title", gameRowMapper);
    }
}
