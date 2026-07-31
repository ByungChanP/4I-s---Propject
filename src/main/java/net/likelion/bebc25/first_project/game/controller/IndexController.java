package net.likelion.bebc25.first_project.game.controller;

import net.likelion.bebc25.first_project.game.dto.GameDto;
import net.likelion.bebc25.first_project.game.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    private final GameService gameService;
    private final String target_game = "";

    public IndexController(GameService gameService) {
        this.gameService = gameService;
    }

    // 전체 게시판 목록 조회
    @GetMapping("/")
    public String home() {return "redirect:/main";}

    @GetMapping("/main")
    public String getGames(Model model) {
        List<GameDto> games = gameService.getGames();
        model.addAttribute("games", games);
        return "board/main";
    }

    @GetMapping("/board/*")
    public String getBoard() {
        return "board/list";
    }
}
