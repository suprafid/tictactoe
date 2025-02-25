package com.tictactoe.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GameConfig {
    private int boardSize = 3;

    public void setBoardSize(int boardSize) {
        if (boardSize < 3 || boardSize > 10) {
            throw new IllegalArgumentException("Board size must be between 3 and 10.");
        }
        this.boardSize = boardSize;
    }
}
