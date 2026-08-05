package net.likelion.bebc25.first_project.user_game_info.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.member.dto.SessionMemberDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkInfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.LostarkIngameInfoDto;
import net.likelion.bebc25.first_project.user_game_info.service.UserGameInfoService;
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


    @GetMapping("/{id}/profile")
    public String getInfoForm(@PathVariable("id") int gameId, Model model) {

        GameDto game = gameService.getGame(gameId);
        model.addAttribute("game", game);

        return "member/profile";
    }

    @PostMapping("/{gameId}/profile")
    public String createProfile(
            @PathVariable int id,
            @ModelAttribute("ingameInfo") Object ingameInfoDto,
            HttpSession session
    ) {
        SessionMemberDto loginMember = (SessionMemberDto) session.getAttribute("loginMember");
        int memberId = loginMember.getId();

        if (id == 1) {

            IngameInfoDto lolDto =
                    (IngameInfoDto) ingameInfoDto;

            userGameInfoService.register(
                    id,
                    memberId,
                    lolDto

            );

        } else if (id == 2) {

            LostarkIngameInfoDto lostarkDto =
                    (LostarkIngameInfoDto) ingameInfoDto;

            userGameInfoService.register(
                    id,
                    memberId,
                    lostarkDto
            );
        }
        return "redirect:/board/" + id;
    }

    @ModelAttribute("ingameInfo")
    public Object ingameInfo(
            @PathVariable("id") int gameId
    ) {

        if (gameId == 1) {
            return new IngameInfoDto();
        }

        if (gameId == 2) {
            return new LostarkIngameInfoDto();
        }

        throw new IllegalArgumentException("지원하지 않는 게임입니다.");
    }

}
