package com.tictactoe.model;

import lombok.Getter;

public class Board {
    @Getter
    private final int size;
    private final char[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = '-';
            }
        }
    }

    /**
     * Places a player's move on the board if the cell is empty.
     */
    public boolean makeMove(int row, int col, char player) {
        if (isValidMove(row, col)) {
            grid[row][col] = player;
            return true;
        }
        return false;
    }

    /**
     * Checks if the given move is valid.
     */
    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size && grid[row][col] == '-';
    }

    /**
     * Returns the current state of the board.
     */
    public char[][] getGrid() {
        char[][] copy = new char[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(grid[i], 0, copy[i], 0, size);
        }
        return copy;
    }

    /**
     * Checks if a player has won the game.
     */
    public boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < size; i++) {
            if (isWinningLine(player, grid[i]) || isWinningColumn(player, i)) {
                return true;
            }
        }
        // Check diagonals
        return isWinningMainDiagonal(player) || isWinningAntiDiagonal(player);
    }

    /**
     * Checks if the board is full (i.e., a draw).
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (grid[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWinningLine(char player, char[] line) {
        for (char cell : line) {
            if (cell != player) return false;
        }
        return true;
    }

    private boolean isWinningColumn(char player, int col) {
        for (int i = 0; i < size; i++) {
            if (grid[i][col] != player) return false;
        }
        return true;
    }

    private boolean isWinningMainDiagonal(char player) {
        for (int i = 0; i < size; i++) {
            if (grid[i][i] != player) return false;
        }
        return true;
    }

    private boolean isWinningAntiDiagonal(char player) {
        for (int i = 0; i < size; i++) {
            if (grid[i][size - 1 - i] != player) return false;
        }
        return true;
    }
}
