package net.likelion.bebc25.first_project.post.repository;

import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.post.dto.PostDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
public class JdbcTemplatePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PostDto> postRowMapper = (ResultSet rs, int rowNum) -> {
        return PostDto.builder()
                .id(rs.getInt("id"))
                .memberId(rs.getInt("member_id"))
                .gameId(rs.getInt("game_id"))
                .title(rs.getString("title"))
                .tag(rs.getString("tag"))
                .content(rs.getString("content"))
                .maxParticipantCount(rs.getInt("max_count"))
                .participantCount(rs.getInt("participant_count"))
                .createdAt(rs.getObject("created_at", LocalDateTime.class))
                .deadline(rs.getObject("deadline", LocalDateTime.class))
                .isClosed(rs.getBoolean("is_closed"))
                .build();
    };

    @Override
    public List<PostDto> findAll(int gameId) {
        log.info("전체 게시글 목록 조회");
        List<PostDto> posts = jdbcTemplate.query("SELECT * FROM post WHERE game_id = ? ORDER BY created_at DESC", postRowMapper, gameId);
        checkDeadline(posts);
        posts = jdbcTemplate.query("SELECT * FROM post WHERE game_id = ? ORDER BY created_at DESC", postRowMapper, gameId);
        return posts;
    }

    @Override
    public List<PostDto> findByTag(int gameId, String tag) {
        log.info("카테고리 별 게시글 목록 조회 - game_id: {}, tag: {}", gameId, tag);
        String sql = "SELECT * FROM post WHERE game_id = ? AND tag = ? ORDER BY created_at DESC";
        List<PostDto> posts = jdbcTemplate.query(sql, postRowMapper, gameId, tag);
        return posts;
    }


    @Override
    public PostDto findById(int id) {
        checkDeadline(id);
        return jdbcTemplate.queryForObject("SELECT * FROM post WHERE id = ?", postRowMapper, id);
    }

    @Override
    public void save(PostDto post) {
        log.info("repository save = {}", post);
        jdbcTemplate.update("INSERT INTO post(game_id, member_id, title, tag, restrictions, content, max_count, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                , post.getGameId()
                , post.getMemberId()
                , post.getTitle()
                , post.getTag()
                , post.getRestrictionString()
                , post.getContent()
                , post.getMaxParticipantCount()
                , post.getDeadline());
    }

    @Override
    public void update(PostDto post) {
        jdbcTemplate.update("UPDATE post SET title = ?, tag = ?, restrictions = ?, content = ?, max_count = ?, deadline = ? WHERE id = ?",
                            post.getTitle(),
                            post.getTag(),
                            post.getRestrictionString(),
                            post.getContent(),
                            post.getMaxParticipantCount(),
                            post.getDeadline(),
                            post.getId());

        checkDeadline(post.getId());
    }

    @Override
    public void updateParticipantCount(int postId, String calcType) {
        PostDto post = findById(postId);
        int updatedValue = 0;
        if (calcType.equals("add")) {updatedValue = 1;}
        if (calcType.equals("remove")) {updatedValue = -1;}
        int updatedParticipantCount = post.getParticipantCount() + updatedValue;
        jdbcTemplate.update("UPDATE post SET participant_count = ? WHERE id = ?", updatedParticipantCount, postId);
        if (updatedParticipantCount == post.getMaxParticipantCount()) {
            jdbcTemplate.update("UPDATE post SET is_closed = true WHERE id = ?", postId);
        }
    }

    @Override
    public void close(int postId) {
        jdbcTemplate.update("UPDATE post SET is_closed = true WHERE id = ?", postId);
    }

    @Override
    public void checkDeadline(int id) {
        jdbcTemplate.update("UPDATE post SET is_closed = true WHERE id = ? AND is_closed = false AND deadline <= NOW()", id);
    }

    @Override
    public void checkDeadline(List<PostDto> posts) {
        for (PostDto post : posts) {checkDeadline(post.getId());}
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM post WHERE id = ?", id);
    }
}
