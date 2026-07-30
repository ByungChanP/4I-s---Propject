package net.likelion.bebc25.first_project.post.repository;

import net.likelion.bebc25.first_project.post.dto.PostDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class JdbcTemplatePostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PostDto> postRowMapper = (ResultSet rs, int rowNum) -> {
        return null;
    };

    @Override
    public List<PostDto> findAll() {
        return null;
    }

    @Override
    public PostDto findById(int id) {
        return null;
    }

    @Override
    public void save(PostDto post) {

    }

    @Override
    public void update(PostDto post) {

    }

    @Override
    public void deleteById(int id) {

    }
}
