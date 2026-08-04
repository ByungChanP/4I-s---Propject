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

    public JdbcTemplateMemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private final RowMapper<MemberDto> memberRowMapper = (ResultSet rs, int rowNum) -> {
        return MemberDto.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .password(rs.getString("pw"))
                .nickname(rs.getString("nickname"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .profileImgDir(rs.getString("profile_img_dir"))
                .build();
    };


    @Override
    public void save(MemberDto member) {
        jdbcTemplate.update("INSERT INTO member (nickname, email, pw) VALUES (?,?,?)"
                , member.getNickname()
                , member.getEmail()
                , member.getPassword());
    }


    @Override
    public MemberDto findByEmail(String email) {
        return jdbcTemplate.queryForObject("SELECT * FROM member WHERE email = ?", memberRowMapper, email);
    }


    @Override
    public MemberDto findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM member WHERE id = ?", memberRowMapper, id);
    }

    @Override
    public void update(MemberDto member) {
        jdbcTemplate.update("UPDATE member SET nickname = ?, email = ?, pw = ?, profile_img_dir = ? WHERE id = ?"
                , member.getNickname()
                , member.getEmail()
                , member.getPassword()
                , member.getProfileImgDir()
                , member.getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM member WHERE id = ?", id);
    }


    @Override
    public List<MemberDto> findAll() {
        return jdbcTemplate.query("SELECT * FROM member ORDER BY id DESC", memberRowMapper);
    }
}
