package chess.controller;

import chess.service.ChessService;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    private final ChessService chessService;

    public WebController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/game")
    public RedirectView redirectGame(@RequestParam String gameName, @RequestParam String password,
                                     HttpSession session) {
        final int gameId = chessService.getGameId(gameName, password);
        return new RedirectView("/game/" + gameId);
    }

    @GetMapping("/game/{gameId}")
    public String joinGame() {
        return "/game.html";
    }
}
