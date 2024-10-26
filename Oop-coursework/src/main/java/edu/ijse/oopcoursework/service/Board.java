package edu.ijse.oopcoursework.service;

import javafx.scene.layout.GridPane;

public interface Board {
    BoardUI getBoardUI();
    void initializeBoard();
    boolean isLegalMove(int row, int col);
    void updateMove(int row, int col, Piece piece);
    Piece checkWinner();
    void printBoard();
}