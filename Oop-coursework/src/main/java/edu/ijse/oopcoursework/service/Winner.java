package edu.ijse.oopcoursework.service;

public class Winner {
    public Piece winningPiece;
    public int col1, col2, col3;
    public int row1, row2, row3;

    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2, int col3, int row3) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
        this.col3 = col3;
        this.row3 = row3;

        System.out.println("Winner is " + winningPiece);
//        boardController.loadResponse(winningPiece.toString());
    }

}