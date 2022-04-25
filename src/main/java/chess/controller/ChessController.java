package chess.controller;

import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.response.ChessGameResponse;
import chess.controller.dto.response.ErrorResponse;
import chess.controller.dto.response.StatusResponse;
import chess.service.ChessService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class ChessController {

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    @GetMapping("/{gameId}")
    public ChessGameResponse loadGame(@PathVariable long gameId) {
        return chessService.loadGame(gameId);
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<ChessGameResponse> createGame(@PathVariable long gameId) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/games/" + gameId)).body(chessService.createGame(gameId));
    }

    @PutMapping("/{gameId}")
    public ChessGameResponse startOrRestartGame(@PathVariable long gameId) {
        return chessService.startOrRestartGame(gameId);
    }

    @PutMapping("/{gameId}/pieces")
    public ChessGameResponse movePiece(@PathVariable long gameId, @RequestBody MoveRequest moveRequest) {
        return chessService.move(gameId, moveRequest);
    }

    @GetMapping("/{gameId}/status")
    public StatusResponse status(@PathVariable long gameId) {
        return chessService.status(gameId);
    }

    @GetMapping("/{gameId}/end")
    public ChessGameResponse endGame(@PathVariable long gameId) {
        return chessService.end(gameId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleDataNotFound() {
        return ResponseEntity.notFound().build();
    }
}

