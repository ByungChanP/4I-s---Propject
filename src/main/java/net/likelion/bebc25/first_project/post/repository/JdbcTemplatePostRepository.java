package net.likelion.bebc25.first_project.post.repository;

import net.likelion.bebc25.first_project.post.dto.PostDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcTemplatePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PostDto> postRowMapper = (ResultSet rs, int rowNum) -> {
        return PostDto.builder()
                .id(rs.getInt("id"))
                .gameId(rs.getInt("game_id"))
                .title(rs.getString("title"))
                .author(rs.getString("author"))
                .content(rs.getString("content"))
                .maxParticipantCount(rs.getInt("maxParticipantCount"))
                .participantCount(rs.getInt("participantCount"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .tag(rs.getString("tag"))
                .deadline(rs.getObject("deadline", LocalDateTime.class))
                .build();
    };

    @Override
    public List<PostDto> findAll() {
        return jdbcTemplate.query("SELECT * FROM post ORDER BY id DESC", postRowMapper);
    }

    @Override
    public PostDto findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM post WHERE id = ?", postRowMapper, id);
    }

    @Override
    public void save(PostDto post) {
         jdbcTemplate.update("INSERT INTO post(game_id, title, tag, author, content, maxParticipantCount, participantCount, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                , post.getGameId()
                , post.getTitle()
                , post.getTag()
                , post.getAuthor()
                , post.getContent()
                , post.getMaxParticipantCount()
                , post.getParticipantCount()
                , post.getDeadline());

    }

    @Override
    public void update(PostDto post) {
        jdbcTemplate.update("UPDATE post SET title = ?, tag = ?, content = ?, maxParticipantCount = ?, participantCount = ?, deadlinde = ? WHERE id = ?"
                , post.getTitle()
                , post.getTag()
                , post.getContent()
                , post.getMaxParticipantCount()
                , post.getParticipantCount()
                , post.getDeadline()
                , post.getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM post WHERE id = ?", id);
    }
}
