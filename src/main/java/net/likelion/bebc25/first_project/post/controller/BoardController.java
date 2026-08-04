package net.likelion.bebc25.first_project.post.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.member.dto.MemberDto;
import net.likelion.bebc25.first_project.member.dto.SessionMemberDto;
import net.likelion.bebc25.first_project.member.service.MemberService;
import net.likelion.bebc25.first_project.party_registration.dto.PartyRegistrationDto;
import net.likelion.bebc25.first_project.party_registration.service.PartyRegistrationService;
import net.likelion.bebc25.first_project.post.dto.PostDto;
import net.likelion.bebc25.first_project.post.service.PostService;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.service.UserGameInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;

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
    private final PartyRegistrationService partyRegistrationService;
    private final UserGameInfoService userGameInfoService;
    private final ObjectMapper objectMapper;

    public BoardController(PostService postService, GameService gameService, MemberService memberService, PartyRegistrationService partyRegistrationService, UserGameInfoService userGameInfoService, ObjectMapper objectMapper) {
        this.postService = postService;
        this.gameService = gameService;
        this.memberService = memberService;
        this.partyRegistrationService = partyRegistrationService;
        this.userGameInfoService = userGameInfoService;
        this.objectMapper = objectMapper;
    }

    // 게시글 목록 조회
    @GetMapping("/{id}")
    public String getPosts(@PathVariable("id") int gameId, Model model) {
        log.info("게시글 목록");
        List<PostDto> posts = postService.getPosts(gameId);
        model.addAttribute("posts", posts);

        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);

        return "board/list";
    }

    // 게시글 상세 조회
    @GetMapping("/{gameId}/detail")
    public String getDetail(
            @PathVariable("gameId") int gameId, @RequestParam int id,
            HttpSession session, Model model) {
        log.info("게시글 상세조회");
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);

        MemberDto member = memberService.getMember(post.getMemberId());
        model.addAttribute("member", member);

        GameDto game = gameService.getGame(post.getGameId());
        model.addAttribute("game", game);

        InfoDto authorIngameInfo = userGameInfoService.getInfo(gameId, post.getMemberId());
        model.addAttribute("authorIngameInfo", authorIngameInfo);
        model.addAttribute("authorPosition", String.join(", ", authorIngameInfo.getIngame_info().getPosition()));

        // 파티 참가자 목록 조회
        // 현재 로그인한 사용자가 이 파티에 참가했는지 확인하는 로직 포함
        List<PartyRegistrationDto> participantInfoList = partyRegistrationService.getRegistrations(post.getId());
        boolean isParticipant = false;
        SessionMemberDto loginMember = (SessionMemberDto) session.getAttribute("loginMember");
        for (PartyRegistrationDto participant : participantInfoList) {
            if (participant.getMemberId() == loginMember.getId()) {isParticipant = true;}
            IngameInfoDto participantIngameInfo = objectMapper.readValue(participant.getParticipantInfoString(), IngameInfoDto.class);
            participant.setParticipantInfo(participantIngameInfo);
            participant.setMemberNickname(memberService.getMember(participant.getMemberId()).getNickname());
            participant.setPosition(String.join(", ", participantIngameInfo.getPosition()));
        }
        model.addAttribute("isParticipant", isParticipant);
        model.addAttribute("participants", participantInfoList);

        int remainTimeInMinute = (int) Duration.between(LocalDateTime.now(), post.getDeadline()).toMinutes();
        String remainTimeInString = String.format("%d시간 %d분 남음", remainTimeInMinute / 60, remainTimeInMinute % 60);
        model.addAttribute("remainTime", remainTimeInString);

        return "board/detail"; // 템플릿 파일 경로
    }

    // 게시글 작성화면 요청
    @GetMapping("/{gameId}/write")
    public String getWriteForm(
            @PathVariable("gameId") int gameId,
            Model model
    ) {
        PostDto postDto = new PostDto();
        postDto.setGameId(gameId);
        model.addAttribute("postDto", postDto);
        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);
        return "board/write";
    }

    // 게시글 수정화면 요청
    @GetMapping("/{gameId}/edit")
    public String getEditForm(@PathVariable("gameId") int gameId, @RequestParam int id, Model model) {
        PostDto post = postService.getPost(id);
        post.setGameId(gameId);
        model.addAttribute("postDto", post);
        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);
        log.info("게시글 수정");
        return "board/edit";
    }

    // 게시글 등록 요청
    @PostMapping("/{gameId}/write")
    public String writePost(
            @PathVariable int gameId,
            @ModelAttribute PostDto postDto,
            HttpSession session
    ) {
        SessionMemberDto loginMember = (SessionMemberDto) session.getAttribute("loginMember");
        postDto.setMemberId(loginMember.getId());
        String restriction = objectMapper.writeValueAsString(postDto.getRestriction());
        postDto.setRestrictionString(restriction);
        postService.writePost(postDto);
        return "redirect:/board/" + gameId;
    }

    // 게시글 수정 요청
    @PostMapping("/{gameId}/edit")
    public String editPost(@PathVariable int gameId,
                           @ModelAttribute PostDto postDto) {
        postService.editPost(postDto);
        return "redirect:/board/" + gameId + "/detail?id=" + postDto.getId();
    }

    // 게시글 삭제 요청
    @PostMapping("/{gameId}/delete")
    public String deletePost(@PathVariable int gameId, @RequestParam int postId) {
        postService.removePost(postId);
        return "redirect:/board/" + gameId;
    }

    // 파티원 모집 마감 요청
    @PostMapping("/*/request:close")
    public String closePost(@PathVariable int gameId, @RequestParam("id") int id, Model model) {
        log.info("파티원 모집 마감");
        PostDto post = postService.getPost(id);
        model.addAttribute("post", post);
        return "redirect:/board/" + gameId + "/detail?id=" + post.getId();
    }

    // 참가요청
    @PostMapping("/{gameId}/{postId}/join")
    public String joinParty(
            @PathVariable int gameId, @PathVariable int postId,
            HttpSession session
    ) {
        SessionMemberDto sessionMember = (SessionMemberDto) session.getAttribute("loginMember");
        InfoDto participantIngameInfo = userGameInfoService.getInfo(gameId, sessionMember.getId());

        PartyRegistrationDto registration = new PartyRegistrationDto();
        registration.setPostId(postId);
        registration.setMemberId(sessionMember.getId());
        registration.setParticipantInfoString(objectMapper.writeValueAsString(participantIngameInfo.getIngame_info()));

        partyRegistrationService.register(registration);
        return "redirect:/board/%d/detail?id=%d".formatted(gameId, postId);
    }

    // 참가 거부
    @PostMapping("/*/request:refuse")
    public String refuseParticipant() {
        log.info("참가거부");
        return "redirect:/board/leagueoflegend/detail";
    }
}