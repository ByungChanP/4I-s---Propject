package net.likelion.bebc25.first_project.post.controller;

import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.member.dto.MemberDto;
import net.likelion.bebc25.first_project.member.service.MemberService;
import net.likelion.bebc25.first_project.post.dto.PostDto;
import net.likelion.bebc25.first_project.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final PostService postService;
    private final GameService gameService;
    private final MemberService memberService;

    public BoardController(PostService postService, GameService gameService, MemberService memberService) {
        this.postService = postService;
        this.gameService = gameService;
        this.memberService = memberService;
    }

    // 게시글 목록 조회
    @GetMapping("/{id}")
    public String getPosts(@PathVariable("id") int gameId, Model model) {
        log.info("게시글 목록");
        List<PostDto> posts = postService.getPosts();
        model.addAttribute("posts", posts);

        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);

        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/*/detail")
    public String getDetail(@RequestParam int id, Model model1, Model model2, Model model3, Model remainTime) {
        log.info("게시글 상세조회");
        PostDto post = postService.getPost(id);
        MemberDto member = memberService.getMember(post.getMemberId());
        GameDto game = gameService.getGame(post.getGameId());
        model1.addAttribute("post", post);
        model2.addAttribute("member", member);
        model3.addAttribute("game", game);

        int remainTimeInMinute = (int) Duration.between(LocalDateTime.now(), post.getDeadline()).toMinutes();
        String remainTimeInString = String.format("%d시간 %d분 남음", remainTimeInMinute / 60, remainTimeInMinute % 60);
        remainTime.addAttribute("remainTime", remainTimeInString);
        return "board/detail"; // 템플릿 파일 경로
    }

    // 게시글 작성화면 요청
    @GetMapping("/*/write_post")
    public String getWriteForm(Model model) {
        model.addAttribute("postDto", new PostDto());
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
    public String writePost(@ModelAttribute PostDto postDto) {

        log.info("postDto = {}", postDto);
        postService.writePost(postDto);

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
    public String closePost(@RequestParam("id") int id, Model model) {
        log.info("파티원 모집 마감");
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        return "redirect:/board/leagueoflegend/detail?id=" + post.getId();
    }

    // 참가요청
    @PostMapping("/*/request:join")
    public String joinParty() {
        log.info("파티 참가 신청");
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