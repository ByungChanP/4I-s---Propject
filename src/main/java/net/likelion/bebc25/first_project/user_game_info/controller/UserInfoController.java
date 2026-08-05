package net.likelion.bebc25.first_project.user_game_info.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.member.dto.SessionMemberDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LolIngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkIngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.service.UserGameInfoService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@Slf4j
@RequestMapping("/board")
public class UserInfoController {

    private final UserGameInfoService userGameInfoService;
    private final GameService gameService;

    public UserInfoController(UserGameInfoService userGameInfoService, GameService gameService) {
        this.userGameInfoService = userGameInfoService;
        this.gameService = gameService;
    }


    @GetMapping("/{gameId}/profile")
    public String getInfoForm(@PathVariable int gameId, HttpSession session, Model model) {
        SessionMemberDto sessionMember = (SessionMemberDto) session.getAttribute("loginMember");
        try {
            userGameInfoService.getInfo(gameId, sessionMember.getId());
            model.addAttribute("ingameProfileExist", true);
        } catch (EmptyResultDataAccessException _) {
            model.addAttribute("ingameProfileExist", false);
        }
        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);

        return "member/profile";
    }

    @PostMapping("/{gameId}/profile")
    public String createProfile(
            @PathVariable("gameId") int gameId,
            @ModelAttribute("ingameInfo") Object ingameInfoDto,
            HttpSession session
    ) {
        SessionMemberDto loginMember = (SessionMemberDto) session.getAttribute("loginMember");
        int memberId = loginMember.getId();

        if (gameId == 1) {

            LolIngameInfoDto lolDto =
                    (LolIngameInfoDto) ingameInfoDto;

            userGameInfoService.register(
                    memberId,
                    gameId,
                    lolDto
            );

        }
        else if (gameId == 2) {

            LostarkIngameInfoDto lostarkDto =
                    (LostarkIngameInfoDto) ingameInfoDto;

            userGameInfoService.register(
                    memberId,
                    gameId,
                    lostarkDto
            );
        }
        return "redirect:/board/" + gameId;
    }

    @ModelAttribute("ingameInfo")
    public Object ingameInfo(
            @PathVariable("gameId") int gameId
    ) {

        if (gameId == 1) {
            return new LolIngameInfoDto();
        }

        if (gameId == 2) {
            return new LostarkIngameInfoDto();
        }

        throw new IllegalArgumentException("지원하지 않는 게임입니다.");
    }

}
