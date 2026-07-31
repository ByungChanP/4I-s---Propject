package net.likelion.bebc25.first_project.post.controller;

import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.post.dto.PostDto;
import net.likelion.bebc25.first_project.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;
    private final GameService gameService;

    public BoardController(PostService postService, GameService gameService) {
        this.postService = postService;
        this.gameService = gameService;
    }

    // 게시글 목록 조회
    @GetMapping("/")
    public String getPosts(Model model) {
        log.info("게시글 목록");
        List<PostDto> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/*/detail")
    public String getDetail() {
        log.info("게시글 상세조회");
        return "board/detail"; // 템플릿 파일 경로
    }

    // 게시글 작성화면 요청
    @GetMapping("/*/write_post")
    public String getWriteForm() {
        log.info("게시글 작성");
        return "board/write";
    }

    // 게시글 수정화면 요청
    @GetMapping("/*/edit")
    public String getEditForm() {
        log.info("게시글 수정");
        return "board/edit";
    }

    // 게시글 등록 요청
    @PostMapping("/*/request:post")
    public String writePost() {
        log.info("게시글 등록요청");
        return "redirect:/board/leagueoflegend";
    }

    // 게시글 수정 요청
    @PostMapping("/*/request:edit_post")
    public String editPost() {
        log.info("게시글 수정요청");
        return "redirect:/board/leagueoflegend";
    }

    // 게시글 삭제 요청
    @PostMapping("/*/request:delete_post")
    public String deletePost() {
        log.info("게시글 삭제요청");
        return "redirect:/board/leagueoflegend";
    }

    // 파티원 모집 마감 요청
    @PostMapping("/*/request:close")
    public String closePost() {
        log.info("파티원 모집 마감");
        return "redirect:/board/leagueoflegend/detail";
    }

    // 참가 거부
    @PostMapping("/*/request:refuse")
    public String refuseParticipant() {
        log.info("참가거부");
        return "redirect:/board/leagueoflegend/detail";
    }

    // 평점 제출
    @PostMapping("/*/request:rating")
    public String rating() {
        log.info("별점 제출");
        return "redirect:/board/leagueoflegend/detail";
    }
}