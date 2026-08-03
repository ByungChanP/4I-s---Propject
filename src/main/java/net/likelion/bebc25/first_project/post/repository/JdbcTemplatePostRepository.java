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
    public List<PostDto> findAll() {
        log.info("전체 게시글 목록 조회");
        List<PostDto> posts = jdbcTemplate.query("SELECT * FROM post ORDER BY created_at DESC", postRowMapper);
        checkDeadline(posts);
        posts = jdbcTemplate.query("SELECT * FROM post ORDER BY created_at DESC", postRowMapper);
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
            jdbcTemplate.update("INSERT INTO post(game_id, title, tag, member_id, min_rank, max_rank, content, max_count, participant_count, deadline) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    , 0 // 테스트를 위해 0
                    , post.getTitle()
                    , post.getTag()
                    , 1 // 테스트를 위해 1 (아직 로그인과 HttpSession구현이 안됨)
                    , post.getMinRank()
                    , post.getMaxRank()
                    , post.getContent()
                    , post.getMaxParticipantCount()
                    , 1 // 글을 처음 작성할 때는 현재 참가자는 무조건 1명 (글 작성자)
                    , post.getDeadline());
    }

    @Override
    public void update(PostDto post) {
        jdbcTemplate.update("UPDATE post SET title = ?, tag = ?, content = ?, max_count = ?, participant_count = ?, deadline = ? WHERE id = ?",
                            post.getTitle(),
                            post.getTag(),
                            post.getContent(),
                            post.getMaxParticipantCount(),
                            post.getParticipantCount(),
                            post.getDeadline(),
                            post.isClosed(),
                            post.getId());

        checkDeadline(post.getId());
    }

    @Override
    public void close(PostDto post) {
        jdbcTemplate.update("UPDATE post SET is_closed = true WHERE id = ?", post.getId());
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
