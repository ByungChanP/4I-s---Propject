package net.likelion.bebc25.first_project.post.service;

import net.likelion.bebc25.first_project.post.dto.PostDto;
import net.likelion.bebc25.first_project.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(@Qualifier("jdbcTemplatePostRepository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getPosts(int gameId) {
        return postRepository.findAll(gameId);
    }

    @Override
    public PostDto getPost(int id) {
        return postRepository.findById(id);
    }

    @Override
    public void writePost(PostDto post) {
        postRepository.save(post);
    }

    @Override
    public void editPost(PostDto post) {
        postRepository.update(post);
    }

    @Override
    public void closePost(PostDto post) {
        postRepository.close(post);
    }

    @Override
    public void removePost(int id) {
        postRepository.deleteById(id);
    }
}
