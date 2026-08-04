package net.likelion.bebc25.first_project.user_game_info.controller;

import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.member.dto.MemberDto;
import net.likelion.bebc25.first_project.member.dto.SessionMemberDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.InfoDto;
import net.likelion.bebc25.first_project.user_game_info.InfoDto.IngameInfoDto;
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
        model.addAttribute("ingameInfoDto", new IngameInfoDto());

        return "member/profile";
    }

    @PostMapping("/{id}/profile")
    public String createProfile(
            @PathVariable int id,
            @RequestParam("memberId") int memberId,
            @ModelAttribute IngameInfoDto ingameInfoDto

    ) {
        userGameInfoService.register(
                id,
                memberId,
                ingameInfoDto
        );
        return "redirect:/board/" +  id;
    }

}
