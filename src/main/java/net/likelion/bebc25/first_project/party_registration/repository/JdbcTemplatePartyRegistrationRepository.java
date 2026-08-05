package net.likelion.bebc25.first_project.party_registration.repository;

import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class JdbcTemplatePartyRegistrationRepository implements PartyRegistrationRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePartyRegistrationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PartyRegistrationDto> registrationMapper = (ResultSet rs, int rowNum) -> {
        return PartyRegistrationDto.builder()
                .id(rs.getInt("id"))
                .postId(rs.getInt("post_id")).
                memberId(rs.getInt("member_id")).
                participantInfoString(rs.getString("participant_info"))
                .build();
    };

    @Override
    public void save(PartyRegistrationDto registration) {
        jdbcTemplate.update("INSERT INTO participant_info (post_id, member_id, participant_info) VALUES (?, ?, ?)",
                            registration.getPostId(),
                            registration.getMemberId(),
                            registration.getParticipantInfoString()
        );
    }

    @Override
    public void remove(int registrationId) {
        jdbcTemplate.update("DELETE FROM participant_info WHERE id = ?", registrationId);
    }

    @Override
    public List<PartyRegistrationDto> findRegistrations(int postId) {
        return jdbcTemplate.query("SELECT * FROM participant_info WHERE post_id = ?", registrationMapper, postId);
    }
}
