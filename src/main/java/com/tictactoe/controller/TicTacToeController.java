package com.tictactoe.controller;

import com.tictactoe.service.TicTacToeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tictactoe")
@RequiredArgsConstructor
public class TicTacToeController {
    private final TicTacToeService ticTacToeService;

    @GetMapping("/start")
    public String startGame(@RequestParam char player) {
        return ticTacToeService.startGame(player);
    }

    @GetMapping("/move")
    public Map<String, Object> makeMove(@RequestParam int row, @RequestParam int col) {
        return ticTacToeService.makeMove(row, col);
    }

    @GetMapping("/board")
    public char[][] getBoard() {
        return ticTacToeService.getBoard();
    }

    @GetMapping("/reset")
    public String resetGame() {
        return ticTacToeService.resetGame();
    }

    @GetMapping("/board-size")
    public String updateBoardSize(@RequestParam int size) {
        return ticTacToeService.updateBoardSize(size);
    }
}
