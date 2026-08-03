package net.likelion.bebc25.first_project.post.service;

import net.likelion.bebc25.first_project.post.dto.PostDto;
import net.likelion.bebc25.first_project.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(@Qualifier("jdbcTemplatePostRepository") PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostDto getPost(int id) {
        return postRepository.findById(id);
    }

    @Override
    public void writePost(PostDto post) {
        // 작성 시간
        post.setCreatedAt(LocalDateTime.now());

        // 현재 참가자 수
        post.setParticipantCount(1);

        // 임시 작성자
        post.setAuthor("테스트");

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
