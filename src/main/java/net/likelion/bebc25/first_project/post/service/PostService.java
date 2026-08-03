package net.likelion.bebc25.first_project.post.service;

import net.likelion.bebc25.first_project.post.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getPosts(int gameId);

    PostDto getPost(int id);

    void writePost(PostDto post);

    void editPost(PostDto post);

    void closePost(PostDto post);

    void removePost(int id);
}
