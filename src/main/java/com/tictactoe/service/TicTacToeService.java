package com.tictactoe.service;

import com.tictactoe.config.GameConfig;
import com.tictactoe.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class TicTacToeService {
    private final GameConfig gameConfig;
    private Board board;
    private char playerSymbol;
    private char aiSymbol;
    private boolean gameStarted = false;
    private final Random random = new Random();

    public String startGame(char player) {
        if (gameStarted) {
            return "Game already started!";
        }

        this.playerSymbol = player;
        this.aiSymbol = (player == 'X') ? 'O' : 'X';
        this.board = new Board(gameConfig.getBoardSize());
        this.gameStarted = true;
        return "Game started with board size: " + gameConfig.getBoardSize() + "x" + gameConfig.getBoardSize();
    }

    public Map<String, Object> makeMove(int row, int col) {
        if (!gameStarted) {
            throw new IllegalArgumentException("Game has not started! Start a new game first.");
        }

        // Player move
        if (!board.makeMove(row, col, playerSymbol)) {
            throw new IllegalArgumentException("Invalid move! Cell is already occupied or out of bounds.");
        }

        // Check if player won
        if (board.checkWinner(playerSymbol)) {
            gameStarted = false;
            return createResponse("Player win!!", board.getGrid());
        }

        // AI Move (random)
        makeRandomMove(aiSymbol);

        // Check if AI won
        if (board.checkWinner(aiSymbol)) {
            gameStarted = false;
            return createResponse("AI win!!", board.getGrid());
        }

        // Check for draw
        if (board.isFull()) {
            gameStarted = false;
            return createResponse("Draw!", board.getGrid());
        }

        String message = "Player " + playerSymbol + " moved.";
        return createResponse(message, board.getGrid());
    }

    public char[][] getBoard() {
        return gameStarted ? board.getGrid() : new char[0][0];
    }

    public String resetGame() {
        gameStarted = false;
        return "Game reset!";
    }

    public String updateBoardSize(int size) {
        if (gameStarted) {
            return "Cannot change board size while a game is running!";
        }
        gameConfig.setBoardSize(size);
        return "Board size updated to " + size + "x" + size;
    }

    private void makeRandomMove(char player) {
        if (!gameStarted) return; // If the game ended, AI doesn't move

        int size = board.getSize();
        int row, col;

        do {
            row = random.nextInt(size);
            col = random.nextInt(size);
        } while (!board.makeMove(row, col, player));
    }


    private Map<String, Object> createResponse(String message, char[][] boardState) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("board", boardState);
        return response;
    }
}
