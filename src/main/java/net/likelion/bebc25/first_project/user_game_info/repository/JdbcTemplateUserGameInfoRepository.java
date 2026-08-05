package net.likelion.bebc25.first_project.user_game_info.repository;

import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LolIngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkIngameInfoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import tools.jackson.databind.ObjectMapper;

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

    private RowMapper<InfoDto> userRowMapper(int gameId) {
        return (rs, rowNum) -> {
            String json = rs.getString("ingame_info");

            IngameInfoDto ingameInfoDto;

            switch (gameId) {
                case 1 -> ingameInfoDto = objectMapper.readValue(json, LolIngameInfoDto.class);
                case 2 -> ingameInfoDto = objectMapper.readValue(json, LostarkIngameInfoDto.class);
                default -> throw new IllegalArgumentException("지원하지 않는 게임입니다.");
            }


            return InfoDto.builder()
                    .id(rs.getInt("id"))
                    .game_id(rs.getInt("game_id"))
                    .member_id(rs.getInt("member_id"))
                    .lolIngameInfo(ingameInfoDto)
                    .build();
        };
    }

    @Override
    public void save(InfoDto info) {
        switch (info.getGame_id()) {
            case 1 -> {
                String ingameInfoJson = objectMapper.writeValueAsString(info.getLolIngameInfo());
                jdbcTemplate.update("INSERT INTO ingame_info (game_id, member_id, ingame_info) VALUES (?,?,?)"
                        , info.getGame_id()
                        , info.getMember_id()
                        , ingameInfoJson);
            }
            case 2 -> {
                String ingameInfoJson = objectMapper.writeValueAsString(info.getLostarkIngameInfo());
                jdbcTemplate.update("INSERT INTO ingame_info (game_id, member_id, ingame_info) VALUES (?,?,?)"
                        , info.getGame_id()
                        , info.getMember_id()
                        , ingameInfoJson);
            }

            default -> throw new RuntimeException("미구현");
        }
    }

    @Override
    public void update(InfoDto gameInfo) {

    }

    @Override
    public InfoDto findByID(int gameId, int memberId) {
        return jdbcTemplate.queryForObject("SELECT * FROM ingame_info WHERE game_id = ? AND member_id = ?", userRowMapper(gameId), gameId, memberId);
    }
}
