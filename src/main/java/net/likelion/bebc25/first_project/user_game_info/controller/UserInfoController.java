package net.likelion.bebc25.first_project.user_game_info.controller;

import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.first_project.game.service.GameService;
import net.likelion.bebc25.first_project.user_game_info.service.UserGameInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/ingame_info")
public class UserInfoController {

    private final UserGameInfoService userGameInfoService;
    private  final GameService gameService;

    public UserInfoController(UserGameInfoService userGameInfoService, GameService gameService) {
        this.userGameInfoService = userGameInfoService;
        this.gameService = gameService;
    }


    @GetMapping("profile")
    public String getInfoForm(Model model){

        return "ingame_info/profile";
    }
}
