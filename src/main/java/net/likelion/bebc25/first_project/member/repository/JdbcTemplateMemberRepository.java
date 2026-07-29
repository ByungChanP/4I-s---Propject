package net.likelion.bebc25.first_project.member.repository;

import net.likelion.bebc25.first_project.member.dto.MemberDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring의 JdbcTemplate을 사용하여 회원 데이터를 처리하는 저장소 구현체입니다.
 */
@Repository
public class JdbcTemplateMemberRepository implements MemberRepository {
    
    private final JdbcTemplate jdbcTemplate;

    /**
     * 생성자를 통해 의존하는 JdbcTemplate을 주입받습니다.
     *
     * @param jdbcTemplate 스프링 빈으로 등록된 JdbcTemplate 객체
     */
    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 데이터베이스 ResultSet 데이터를 MemberDto 객체로 변환해주는 맵퍼 정의입니다.
     */
    private final RowMapper<MemberDto> memberRowMapper = (ResultSet rs, int rowNum) -> {
        return MemberDto.builder()
                .id(rs.getInt("id"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .build();
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(MemberDto member) {
        jdbcTemplate.update("INSERT INTO member2 (username, password, email) VALUES (?, ?, ?)",
                            member.getUsername(), member.getPassword(), member.getEmail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDto findByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT * FROM member2 WHERE username = ?", memberRowMapper, username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MemberDto findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM member2 WHERE id = ?", memberRowMapper, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(MemberDto member) {
        jdbcTemplate.update("UPDATE member2 SET password = ?, email = ? WHERE id = ?"
                , member.getPassword(), member.getEmail(), member.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM member2 WHERE id = ?", id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MemberDto> findAll() {
        return jdbcTemplate.query("SELECT * FROM member2", memberRowMapper);
    }
}
