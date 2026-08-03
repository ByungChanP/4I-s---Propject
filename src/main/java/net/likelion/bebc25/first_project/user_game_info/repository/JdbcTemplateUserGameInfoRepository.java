package net.likelion.bebc25.first_project.user_game_info.repository;

import net.likelion.bebc25.first_project.member.dto.MemberDto;
import net.likelion.bebc25.first_project.user_game_info.dto.InfoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

/**
 * Spring의 JdbcTemplate을 사용하여 프로필 데이터를 처리하는 저장소 구현체입니다.
 */
@Repository
public class JdbcTemplateUserGameInfoRepository implements UserGameInfoRepository{

    private final JdbcTemplate jdbcTemplate;

    /**
     * 생성자를 통해 의존하는 JdbcTemplate을 주입받습니다.
     *
     * @param jdbcTemplate 스프링 빈으로 등록된 JdbcTemplate 객체
     */
    public JdbcTemplateUserGameInfoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 데이터베이스 ResultSet 데이터를 MemberDto 객체로 변환해주는 맵퍼 정의입니다.
     */
    private final RowMapper<InfoDto> memberRowMapper = (ResultSet rs, int rowNum) -> {
        return InfoDto.builder()
                .id(rs.getInt("id"))
                .gameId(rs.getInt("gameId"))
                .
}
