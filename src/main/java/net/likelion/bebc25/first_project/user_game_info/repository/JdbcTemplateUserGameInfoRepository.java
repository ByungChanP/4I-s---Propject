package net.likelion.bebc25.first_project.user_game_info.repository;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkInfoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Spring의 JdbcTemplate을 사용하여 프로필 데이터를 처리하는 저장소 구현체입니다.
 */
@Repository
public class JdbcTemplateUserGameInfoRepository implements UserGameInfoRepository {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public JdbcTemplateUserGameInfoRepository(JdbcTemplate jdbcTemplate, ObjectMapper objectMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    private RowMapper<InfoDto> userRowMapper() {
        return (rs, rowNum) -> {
            String json = rs.getString("ingame_info");

            IngameInfoDto ingameInfoDto;

            try {
                ingameInfoDto = objectMapper.readValue(json, IngameInfoDto.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return InfoDto.builder()
                    .id(rs.getInt("id"))
                    .game_id(rs.getInt("game_id"))
                    .member_id(rs.getInt("member_id"))
                    .ingame_info(ingameInfoDto)
                    .build();
        };
    }

    @Override
    public void save(InfoDto info) {
        try {
            String ingameInfoJson = objectMapper.writeValueAsString(info.getIngame_info());
            jdbcTemplate.update("INSERT INTO ingame_info (game_id, member_id, ingame_info) VALUES (?,?,?)"
                    , info.getGame_id()
                    , info.getMember_id()
                    , ingameInfoJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void save(LostarkInfoDto info) {
        try {
            String ingameInfoJson = objectMapper.writeValueAsString(info.getLostarkInfoDto());
            jdbcTemplate.update("INSERT INTO ingame_info (game_id, member_id, ingame_info) VALUES (?,?,?)"
                    , info.getGame_id()
                    , info.getMember_id()
                    , ingameInfoJson);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(InfoDto gameInfo) {

    }

    @Override
    public List<InfoDto> findByID(int id) {
        return List.of();
    }
}
