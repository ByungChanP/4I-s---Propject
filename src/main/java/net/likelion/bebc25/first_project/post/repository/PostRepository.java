package net.likelion.bebc25.first_project.post.repository;

import net.likelion.bebc25.first_project.post.dto.PostDto;

import java.util.List;

public interface PostRepository {
    List<PostDto> findAll();

    PostDto findById(int id);

    void save(PostDto post);

    void update(PostDto post);

    void close(PostDto post);

    void checkDeadline(int id);

    void checkDeadline(List<PostDto> posts);

    void deleteById(int id);
}
