package edu.ijse.oopcoursework.service;

public class BoardImpl{
    private Piece[][] pieces;
    public static String pieces1 = "N";

    private BoardUI boardUI;
    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;
    }

    public BoardImpl() {

        pieces = new Piece[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    public void updateMove(int row, int col, Piece piece) {
        if (isLegalMove(row, col)) {
            if (isBoardFull()) {
                pieces1 = "draw";
            }
            pieces[row][col] = piece;
        }


        printBoard(piece.toString());
        pieces1 = String.valueOf(checkWinner(true));
        if (isBoardFull()) {
            pieces1 = "draw";
        } 
    }

    public void resetPieces() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    public boolean isLegalMove(int row, int col) {
        boolean isLegal = false;
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return isLegal;
        }
        isLegal = (pieces[row][col] == Piece.EMPTY);
        return isLegal;
    }

    public Piece checkWinner(boolean realMove) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] != Piece.EMPTY && pieces[i][0] == pieces[i][1] && pieces[i][1] == pieces[i][2]) {

                if (realMove) {passToWinner(pieces[i][0], i, 0, i, 1, i, 2);}
                return pieces[i][0];
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (pieces[0][i] != Piece.EMPTY && pieces[0][i] == pieces[1][i] && pieces[1][i] == pieces[2][i]) {

                if(realMove) {passToWinner(pieces[0][i], 0, i, 1, i, 2, i);}
                return pieces[0][i];
            }
        }

        // Check diagonals
        if (pieces[0][0] != Piece.EMPTY && pieces[0][0] == pieces[1][1] && pieces[1][1] == pieces[2][2]) {

            if(realMove){passToWinner(pieces[0][0], 0,0,1,1,2,2);}
            return pieces[0][0];
        }

        if (pieces[0][2] != Piece.EMPTY && pieces[0][2] == pieces[1][1] && pieces[1][1] == pieces[2][0]) {

            if(realMove){passToWinner(pieces[0][2], 0,2,1,1,2,0);}
            return pieces[0][2];
        }

        return null;
    }


    public void printBoard(String player) {
        if (player.equals("O")) {
            //System.out.println("Ai move");
        } else {
            //System.out.println("Your move");
        }

        /*for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (pieces[j][i]) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                        System.out.print(" - ");
                        break;
                }
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("---+---+---");
        }*/
        System.out.println();
    }

    //----------------------------------------------------------------------------------
    public void passToWinner(Piece winnerPiece, int row1, int col1, int row2, int col2, int row3, int col3) {
        Winner winner = new Winner(winnerPiece, row1, col1, row2, col2, row3, col3);
    }

    public Piece[][] getPieces(){
        return pieces;
    }

    public Piece getOpponentPieceX() {
        return Piece.X;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}